package berfin.climatecinema.business.concretes;

import berfin.climatecinema.dataAccess.abstracts.RoleDao;
import berfin.climatecinema.dataAccess.abstracts.UserDao;
import berfin.climatecinema.entities.concretes.Role;
import berfin.climatecinema.entities.concretes.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private UserDao userDao;
    
    private RoleDao roleDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }


    public Role save(Role role) {
        return roleDao.save(role);
    }


    public void addRoleToUser(String username, String roleName) {
        User user = userDao.findByUsername(username);
        Role role = roleDao.findByName(roleName);
        user.getRoles().add(role);
    }


    public User get(String username) {
        return userDao.findByUsername(username);
    }


    public List<User> list() {
        return userDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found.");
        }
        List<SimpleGrantedAuthority> authorities  = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}
