package com.example.weatherprojectcdi.interfaces;


import com.example.weatherprojectcdi.dto.*;
import com.example.weatherprojectcdi.entity.*;
import com.example.weatherprojectcdi.exception.*;

import java.util.*;

public interface IAuthorizationService {

     User create(UserDTO userDTO) throws NotUniqueEmail,InternalServerErrorException;


     Optional<User> find(UserDTO userDTO);

}
