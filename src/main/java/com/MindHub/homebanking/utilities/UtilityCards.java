package com.MindHub.homebanking.utilities;

public class UtilityCards {
    public static String cardNumberCreate(){
        String cardNumber ="";
        for(int i=0;i<4;i++){
            int num = (int) ((Math.random() * (9999 - 1000)) + 1000);
            if(i!=3){
                cardNumber += num + "-";
            }else {
                cardNumber += num;
            }
        }
        return cardNumber;
    }
    public static int createCVV(){
        int numberNewCard;
        int randomNum;
        randomNum =(int) ((Math.random() * (999 - 100)) + 100);
        numberNewCard = randomNum;
        return numberNewCard;
    }
}
