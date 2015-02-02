package com.clicksmob.test;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.client.ClientProtocolException;

import com.clicksmob.sdk.Offers;
import com.clicksmob.sdk.auth.AuthenticationObjectUserPassword;
import com.clicksmob.sdk.entity.OfferCapsPresentation;
import com.clicksmob.sdk.entity.OfferPresentation;
import com.clicksmob.sdk.entity.UserPayout;
import com.clicksmob.sdk.error.ClicksmobException;

public class TestOfferApiV2UserPassword
{
   /**
    * @param args
    * @return
    * @throws ClientProtocolException
    * @throws IOException
    * @throws ClicksmobException
    */
   private static Offers getAndInitOffersDefault( final String[] args ) throws ClientProtocolException, IOException,
            ClicksmobException
   {
      final AuthenticationObjectUserPassword userPassword = new AuthenticationObjectUserPassword(
               AuthenticationObjectUserPassword.URL_API_LOGIN_DEFAULT, args[ 0 ], args[ 1 ] );
      final Offers offers = new Offers( Offers.URL_API_SECURED_DEFAULT );
      offers.init( userPassword );
      return offers;
   }

   public static void main( final String[] args ) throws ClientProtocolException, IOException, ClicksmobException
   {
      if( args.length != 2 )
      {
         throw new java.lang.ArrayIndexOutOfBoundsException( "\n\n\nInvoke with 2 arguments: email, password!!!\n\n\n" );
      }
      final Offers offers = getAndInitOffersDefault( args );
      int i = 0;
      for( final OfferPresentation offerPresentation : offers.getAllOffers() )
      {
         System.out.print( "#" + ++i + "\t" );
         System.out.println( offerPresentation.name );
      }
      i = 0;
      for( final OfferPresentation offerPresentation : offers.getOffersForPlatformsAndCountries(
               Arrays.asList( "iphone", "ipad" ), Arrays.asList( "tw", "hk" ) ) )
      {
         System.out.println();
         System.out.println( "#" + ++i + " offer " + offerPresentation.name );
         for( final UserPayout userPayout : offerPresentation.userPayouts )
         {
            System.out.println( "\t" + userPayout.toString() );
         }
         if( offerPresentation.offerCaps != null )
         {
            for( final OfferCapsPresentation capsPresentation : offerPresentation.offerCaps )
            {
               System.out.append( "\t\tOffer caps " ).println( capsPresentation.toString() );
            }
         }
      }
   }
}
