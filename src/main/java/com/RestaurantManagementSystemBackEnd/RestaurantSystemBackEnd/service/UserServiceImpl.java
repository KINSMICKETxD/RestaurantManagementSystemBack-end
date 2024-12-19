package com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.service;

import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.dto.RegisterRequestDTO;
import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.entity.Role;
import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.entity.User;
import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.repository.RoleRepository;
import com.RestaurantManagementSystemBackEnd.RestaurantSystemBackEnd.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {



    private UserRepository userRepository;



    private BCryptPasswordEncoder bCryptPasswordEncoder;


    private RoleRepository roleRepository;

    private ModelMapper modelMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;

        this.modelMapper = modelMapper;
    }


    @Override
    public Integer saveUser(RegisterRequestDTO registerRequestDTO) {
        User user = this.modelMapper.map(registerRequestDTO,User.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.addRole(roleRepository.findByName("ROLE_USER").get());
        return userRepository.save(user).getId();
    }

    @Override
    public Optional<User> findByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Integer saveAdmin(RegisterRequestDTO registerRequestDTO) {

        User admin = this.modelMapper.map(registerRequestDTO,User.class);
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        admin.addRole(roleRepository.findByName("ROLE_ADMIN").get());
        return userRepository.save(admin).getId();

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);


        org.springframework.security.core.userdetails.User springUser = null;

        if(optionalUser.isEmpty()){
            throw new BadCredentialsException("Please verifier your username and password");
        }
        User user = optionalUser.get();
        Set<Role> roles = user.getRoles();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Role r : roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(r.getName()));
        }

        springUser = new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                grantedAuthorities
        );

        return springUser;

    }
}
