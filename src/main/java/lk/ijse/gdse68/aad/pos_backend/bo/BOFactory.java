package lk.ijse.gdse68.aad.pos_backend.bo;

import lk.ijse.gdse68.aad.pos_backend.bo.custom.impl.CustomerBoImpl;
import lk.ijse.gdse68.aad.pos_backend.bo.custom.impl.ItemBoImpl;
//import lk.ijse.gdse68.aad.pos_backend.bo.custom.impl.OrderBoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}
    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory=new BOFactory():boFactory;
    }
    public  enum BOTypes{
        CUSTOMER,ITEM,ORDER,ORDER_DETAIL
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){

            case CUSTOMER:
                return new CustomerBoImpl();
            case ITEM:
                return new ItemBoImpl();
//            case ORDER:
//                return new OrderBoImpl();
//             case ORDER_DETAIL:
//                 return new OrderDetailBoImpl();

            default:return null;

        }
    }

}
