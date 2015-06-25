package com.clicksmob.sdk;

import com.clicksmob.sdk.model.CountryCode;
import com.clicksmob.sdk.model.Offers;
import com.clicksmob.sdk.model.PlatformCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.IOUtils;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ClicksMob SDK client implementation. Provide means to fetch Offers.
 *
 * @author Guy Raz Nir
 * @since 13/03/2015
 */
public class Client {

    /**
     * Base URL to perform requests on.
     */
    private final String baseURL;

    /**
     * User identifier.
     */
    private final String userId;

    /**
     * User token.
     */
    private final String userToken;

    /**
     * Class logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    /**
     * Class constructor.
     *
     * @param userId    User identifier.
     * @param userToken User token.
     */
    public Client(String userId, String userToken) {
        baseURL = "http://api.clicksmob.com/";
        this.userId = userId;
        this.userToken = userToken;
    }

    /**
     * Query all offers.
     *
     * @return List of all offers available for the user.
     * @throws SDKException On any error that occurs during operation.
     */
    public Offers getOffers() throws SDKException {
        return getOffers(null, null);
    }

    /**
     * Fetch offers, optionally filter by countries list and platforms list.
     *
     * @param countries Collection of country codes to filter by. Ignored if argument is {@code null} or empty.
     * @param platforms Collection of platform codes to filter by. Ignored if argument is {@code null} or empty.
     * @return Offers fetched from server.
     * @throws SDKException If any error occurs during operations.
     */
    public Offers getOffers(Set<CountryCode> countries, Set<PlatformCode> platforms) throws SDKException {
        Map<String, String> map = new HashMap<>();
        map.put("uid", userId);
        map.put("utoken", userToken);

        addValuesToRequest("countries", countries, map);
        addValuesToRequest("platforms", platforms, map);

        String url = buildURL("/rest/api/v2/services/offers.xml", map);

        try {
            logger.info("Issuing request to fetch offers.");
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            int statusCode = connection.getResponseCode();
            logger.debug("Server responded with HTTP status code {}.", statusCode);

            // If operation failed because either client-side provided invalid information or server side had internal
            // error, generate exception.
            if (statusCode >= 400 && statusCode < 500) {
                throw new ClientSDKException(connection.getResponseCode());
            } else if (statusCode >= 500) {
                throw new ServerSDKException(connection.getResponseCode());
            }

            try (InputStream in = connection.getInputStream()) {
                JAXBContext context = JAXBContext.newInstance(Offers.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();

                InputStreamReader reader = new InputStreamReader(in, "UTF-8");
                Offers offers = (Offers) unmarshaller.unmarshal(reader);

                logger.info("Fetched {} offers successfully from server.", offers.getOffer().size());

                return offers;
            }
        } catch (IOException ex) {
            logger.error("IO error occurred while contacting the server ({}).", ex.getClass().getSimpleName());
            throw new SDKException("IO error occurred while contacting the server.", ex);
        } catch (JAXBException ex) {
            logger.error("Internal error while processing server output ({}).", ex.getClass().getSimpleName());
            throw new SDKException("Internal error while processing server output.", ex);
        }
    }

    /**
     * Build a request URL based on a given relative path and optional set of parameters.
     *
     * @param relativeURL Relative URL of REST resource.
     * @param parameters  Optional set of parameters to add to the request.
     * @return URL generated from given data above.
     */
    protected String buildURL(String relativeURL, Map<String, String> parameters) {
        StringBuilder url = new StringBuilder(256);

        url.append(baseURL);
        if (!baseURL.endsWith("/") && relativeURL.charAt(0) != '/') {
            url.append('/');
        }

        url.append(relativeURL);

        if (parameters != null && !parameters.isEmpty()) {
            url.append("?");
            boolean firstParameter = true;
            for (Map.Entry<String, String> parameter : parameters.entrySet()) {
                if (!firstParameter) {
                    url.append('&');
                }

                url.append(parameter.getKey()).append('=').append(parameter.getValue());
                firstParameter = false;
            }
        }

        return url.toString();
    }

    /**
     * Build a comma-delimited list of a given objects' values.
     *
     * @param values Collection of values.
     * @return List of objects' values.
     */
    protected String toCommaSeparatorList(Collection<?> values) {
        StringBuilder buf = new StringBuilder();
        for (Object o : values) {
            if (buf.length() > 0) {
                buf.append(',');
            }
            buf.append(o.toString());
        }

        return buf.toString();
    }

    /**
     * Add parameter's value to target map. The values are comma-separated. Parameter is only added if <i>values</i> is
     * non-{@code null} and not empty.
     *
     * @param parameterName Parameter name.
     * @param values        Values to add.
     * @param parametersMap Target map to place values.
     */
    protected void addValuesToRequest(String parameterName, Collection<?> values, Map<String, String> parametersMap) {
        if (values != null && values.size() > 0) {
            parametersMap.put(parameterName, toCommaSeparatorList(values));
        }

    }
}
