/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import java.util.ArrayList;
import java.util.List;
import models.Role;

/**
 *
 * @author prabh
 */
public class RoleService {
    public List<Role> getAll() throws Exception {
        List<Role> roles = new ArrayList<>();
        RoleDB roleDB = new RoleDB();
        roles = roleDB.getAll();
        return roles;
    }
}
