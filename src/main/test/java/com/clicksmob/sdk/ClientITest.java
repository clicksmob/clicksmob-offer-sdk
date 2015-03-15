package com.clicksmob.sdk;

import com.clicksmob.sdk.model.CountryCode;
import com.clicksmob.sdk.model.Offers;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Sample client integration test with ClicksMob API.
 *
 * @author Guy Raz Nir
 * @since 15/03/2015
 */
public class ClientITest {

    /**
     * User identifier.
     */
    private static final String USER_ID = "userId";

    /**
     * Assigned OAuth1 token.
     */
    private static final String USER_TOKEN = "token";

    /**
     * Perform a simple query for offers.
     *
     * @throws IOException
     * @throws JAXBException
     */
    @Test
    public void testQuery() throws IOException, JAXBException {
        // Create a new client.
        Client client = new Client(USER_ID, USER_TOKEN);

        // Create filter by country code.
        Set<CountryCode> countryCodes = new HashSet<>();
        countryCodes.add(CountryCode.IL);

        // Fetch offers.
        Offers offers = client.getOffers(countryCodes, null);

        // Make sure we got some reasonable response.
        Assert.assertTrue(offers.getOffer().size() > 0);
    }
}
