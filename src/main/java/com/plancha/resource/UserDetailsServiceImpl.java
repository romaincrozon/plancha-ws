package com.plancha.resource;


import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.plancha.dto.entity.Resource;
import com.plancha.repositories.ResourceRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private ResourceRepository resourceRepository;

    public UserDetailsServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Resource resource = resourceRepository.findByUsername(username);
        if (resource == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(resource.getUsername(), resource.getPassword(), emptyList());
    }
}