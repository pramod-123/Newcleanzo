package com.example.priyamkumar.newcleanzo;

import com.katepratik.msg91api.MSG91;

/**
 * Created by pramod on 9/10/16.
 */
public class MSG91Helper extends MSG91 {

    static final String MSG91KEY="106743ALvZm1U9xRc56dae4f3";
    public MSG91Helper(String no,String bookinID) {
        super(MSG91Helper.MSG91KEY);
        String msg="Your order of  booking id- "+bookinID+" has been successfully delivered . We are extremely glad to  serve you and hope to serve you better in future. Keep CleanZoing!";
        composeMessage("CLENZO",msg);
        to(no);
    }

    public MSG91Helper(String no,String price,String bookingID,String expectedDate) {
        super(MSG91Helper.MSG91KEY);
        String msg="Dear Customer , we have received your order of booking id - "+bookingID+" amounting to Rs. "+price+". you can expect the delivery by "+expectedDate+" and you can see your order status in the app. Thank you !!  keep CleanZoing !!";
        composeMessage("CLENZO",msg);
        to(no);
    }
}