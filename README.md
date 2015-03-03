### ClicksMob Offer Java SDK version 2.0.1

####This Software Development Kit (SDK) enables you to pull the list of promoted offers from Clicksmob servers.  

####Prerequisites:
This SDK requires the following 3-d party components/libraries to be in the classpath of your project:
*  **commons-lang3-3.1.jar**
*  **log4j-1.2.14.jar**
*  **slf4j-api-1.7.5.jar**
*  **jcl-over-slf4j-1.7.5.jar**
*  **slf4j-log4j12-1.7.5.jar**
*  **xstream-1.4.7.jar**
*  **xmlpull-1.1.3.1.jar**
*  **xpp3_min-1.1.4c.jar**
*  **httpclient-4.3.1.jar**
*  **commons-logging-1.1.3.jar**
*  **commons-codec-1.6.jar**
*  **httpcore-4.3.1.jar**

####For maven based projects, ensure that pom.xml includes the following dependencies.
        <dependencies>
                <dependency>
                        <groupId>org.apache.commons</groupId>
                        <artifactId>commons-lang3</artifactId>
                        <version>3.1</version>
                </dependency>
                <dependency>
                        <groupId>log4j</groupId>
                        <artifactId>log4j</artifactId>
                        <version>1.2.14</version>
                        <scope>runtime</scope>
                </dependency>
                <dependency>
                        <groupId>org.slf4j</groupId>
                        <artifactId>slf4j-api</artifactId>
                        <version>1.7.5</version>
                        <scope>compile</scope>
                </dependency>
                <dependency>
                        <groupId>org.slf4j</groupId>
                        <artifactId>jcl-over-slf4j</artifactId>
                        <version>1.7.5</version>
                        <scope>runtime</scope>
                </dependency>
                <dependency>
                        <groupId>org.slf4j</groupId>
                        <artifactId>slf4j-log4j12</artifactId>
                        <version>1.7.5</version>
                        <scope>runtime</scope>
                </dependency>
                <dependency>
                        <groupId>com.thoughtworks.xstream</groupId>
                        <artifactId>xstream</artifactId>
                        <version>1.4.7</version>
                </dependency>
                <dependency>
                        <groupId>org.apache.httpcomponents</groupId>
                        <artifactId>httpclient</artifactId>
                        <version>4.3.1</version>
                </dependency>
                <dependency>
                        <groupId>org.apache.httpcomponents</groupId>
                        <artifactId>httpcore</artifactId>
                        <version>4.3.1</version>
                </dependency>
        </dependencies>

### Here is an example on how to use Clicksmob Offer Java SDK
#### For User/Password authenticaion
<pre>
/*
 * Copyright (c) 2014-2015, Clicksmob Ltd.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * Neither the name of 'Clicksmob Ltd.' nor the names of its contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
<code>
   private static OfferSDK getAndInitOffersDefault( final String[] args ) throws ClientProtocolException, IOException,
            ClicksmobException, JAXBException
   {
      final AuthenticationObjectUserPassword userPassword = new AuthenticationObjectUserPassword(
               AuthenticationObjectUserPassword.URL_API_LOGIN_DEFAULT, args[ 0 ], args[ 1 ] );
      final OfferSDK offers = new OfferSDK( OfferSDK.URL_API_SECURED_DEFAULT );
      offers.init( userPassword );
      return offers;
   }

   public static void main( final String[] args ) throws ClientProtocolException, IOException, ClicksmobException,
            JAXBException
   {
      if( args.length != 2 )
      {
         throw new java.lang.ArrayIndexOutOfBoundsException( "\n\n\nInvoke with 2 arguments: email, password!!!\n\n\n" );
      }
      final OfferSDK offers = getAndInitOffersDefault( args );
      int i = 0;
      for( Offers.Offer offer : offers.getAllOffers().getOffer() )
      {
         System.out.print( i++ );
         System.out.print( ") " );
         System.out.println( offer.getOfferName() );
         for( CountryPlatformSet countryPlatformSet : offer.getOfferCaps().getCountryPlatformSet() )
         {
            System.out.println( "\t Daily offer restrictions: " + countryPlatformSet.getDailyCap() );
            System.out.println( "\t\t Countries" );
            for( CountryCode cc : countryPlatformSet.getCountries().getCountry() )
            {
               System.out.println( "\t\t\t  " + cc );
            }
            System.out.println( "\t\t Platforms" );
            for( PlatformCode pc : countryPlatformSet.getPlatforms().getPlatform() )
            {
               System.out.println( "\t\t\t  " + pc );
            }
         }
         for( OfferPayout offerPayout : offer.getOfferPayouts().getOfferPayout() )
         {
            System.out.println( "\t Offer payout: " + offerPayout.getPayout() );
            System.out.println( "\t\t Countries" );
            for( CountryCode cc : offerPayout.getCountries().getCountry() )
            {
               System.out.println( "\t\t\t  " + cc );
            }
            System.out.println( "\t\t Platforms" );
            for( PlatformCode pc : offerPayout.getPlatforms().getPlatform() )
            {
               System.out.println( "\t\t\t  " + pc );
            }
         }
      }
   }
<code>
</pre>
#### For token based authentication
<pre>
/*
 * Copyright (c) 2014-2015, Clicksmob Ltd.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * Neither the name of 'Clicksmob Ltd.' nor the names of its contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
<code>
   private static OfferSDK getAndInitOffersDefault( final String[] args ) throws ClientProtocolException, IOException,
            ClicksmobException, JAXBException
   {
      final AuthenticationObjectToken aoToken = new AuthenticationObjectToken( args[ 0 ], args[ 1 ] );
      final OfferSDK offers = new OfferSDK( OfferSDK.URL_API_TOKEN_AUTH_DEFAULT );
      offers.init( aoToken );
      return offers;
   }

   public static void main( final String[] args ) throws ClientProtocolException, IOException, ClicksmobException,
            JAXBException
   {
      if( args.length != 2 )
      {
         throw new java.lang.ArrayIndexOutOfBoundsException( "\n\n\nInvoke with 2 arguments: uid, token!!!\n\n\n" );
      }
      final OfferSDK offers = getAndInitOffersDefault( args );
      int i = 0;

      for( Offers.Offer offer : offers.getAllOffers().getOffer() )
      {
         System.out.print( i++ );
         System.out.print( ") " );
         System.out.println( offer.getOfferName() );
         for( CountryPlatformSet countryPlatformSet : offer.getOfferCaps().getCountryPlatformSet() )
         {
            System.out.print( "\t Countries" );
            System.out.println( countryPlatformSet.getCountries() );
            System.out.print( "\t Platforms" );
            System.out.println( countryPlatformSet.getPlatforms() );
         }
      }
   }
</code>
</pre>
### A full demo Eclipse project may be downloaded from this site
#####[Clicksmob Java SDK Demo Project](https://github.com/clicksmob/clicksmob-offer-sdk/tree/master/test-eclipse-project)
### Detailed Javadoc may be downloaded from this location
#####[Javadoc documentation](https://github.com/clicksmob/clicksmob-offer-sdk/tree/master/doc)


