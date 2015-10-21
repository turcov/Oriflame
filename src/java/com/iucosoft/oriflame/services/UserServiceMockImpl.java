
package com.iucosoft.oriflame.services;

import com.iucosoft.oriflame.domain.MyUser;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author iurasun
 */
public class UserServiceMockImpl implements UserServiceIntf{

    private static UserServiceMockImpl instance;

    public static UserServiceMockImpl getInstance() {
       if(instance == null){
        instance =new UserServiceMockImpl();
       }
        
        return instance;
    }

    
    
    Map<Integer, MyUser> map;
    private UserServiceMockImpl() {
        
        map=new HashMap<Integer, MyUser>();
        
        MyUser user=new MyUser(1,"admin", "admin");
        map.put(1, user);
        
    }
    
    
    
    
    @Override
    public boolean isValidUsernameAndPassword(String username, String password) {
       
        Collection<MyUser> allUsers =map.values();
        
        for (MyUser user : allUsers) {
            
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                return true;
            }   
        }
        return false;
    }    

}
