### Clicksmob Offer Java SDK version 1.0.1

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

### Here is the simple example on how to use Clicksmob Offer Java SDK
<pre>
<code>
		public class TestOfferApi
		{

		   public static void main( String[] args ) throws ClientProtocolException, IOException, ClicksmobException
		   {
		      if( args.length != 2 )
		      {
			 throw new java.lang.ArrayIndexOutOfBoundsException( "\n\n\nInvoke with 2 arguments: email, password!!!\n\n\n" );
		      }
		      Offers offers = getAndInitOffersDefault( args );
		      int i = 0;
		      for( OfferPresentation offerPresentation : offers.getAllOffers() )
		      {
			 System.out.print( "#" + ++i + "\t");
			 System.out.println( offerPresentation.name );
		      }
		      i = 0;
		      for( OfferPresentation offerPresentation : offers.getOffersForPlatformsAndCountries(
			       Arrays.asList( "iphone", "ipad" ), Arrays.asList( "tw", "hk" ) ) )
		      {
			 System.out.println();
			 System.out.println( "#" + ++i + " offer " + offerPresentation.name );
			 for( UserPayout userPayout : offerPresentation.userPayouts )
			 {
			    System.out.println( "\t" + userPayout.toString() );
			 }
			 
		      }
		   }

		   private static Offers getAndInitOffersDefault( String[] args ) throws ClientProtocolException, IOException,
			    ClicksmobException
		   {
		      AuthenticationObjectUserPassword userPassword = new AuthenticationObjectUserPassword(
			       AuthenticationObjectUserPassword.URL_API_LOGIN_DEFAULT, args[ 0 ], args[ 1 ] );
		      Offers offers = new Offers( Offers.URL_API_DEFAULT );
		      offers.init( userPassword );
		      return offers;
		   }
		}
		</code>
		</pre>
### A full demo Eclipse project may be downloaded from this site
#####[Clicksmob Java SDK Demo Project](https://github.com/clicksmob/clicksmob-offer-sdk/tree/master/test-eclipse-project)
### Detailed Javadoc may be downloaded from this location
#####[Javadoc documentation](https://github.com/clicksmob/clicksmob-offer-sdk/tree/master/doc)
