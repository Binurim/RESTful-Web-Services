package com.apps.developerblog.app.ws.userservice;

import com.apps.developerblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.apps.developerblog.app.ws.ui.model.response.UserRest;

public interface UserService {
   UserRest createUser(UserDetailsRequestModel userDetails);
}
