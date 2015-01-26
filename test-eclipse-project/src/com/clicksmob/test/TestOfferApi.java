package com.clicksmob.test;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.client.ClientProtocolException;

import com.clicksmob.sdk.Offers;
import com.clicksmob.sdk.auth.AuthenticationObjectUserPassword;
import com.clicksmob.sdk.entity.OfferPresentation;
import com.clicksmob.sdk.entity.UserPayout;
import com.clicksmob.sdk.error.ClicksmobException;

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

   /**
    * @param args
    * @return
    * @throws ClientProtocolException
    * @throws IOException
    * @throws ClicksmobException
    */
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
