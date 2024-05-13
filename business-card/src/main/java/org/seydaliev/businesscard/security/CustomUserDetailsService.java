package org.seydaliev.businesscard.security;

import org.seydaliev.businesscard.model.Profile;
import org.seydaliev.businesscard.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Profile> profile = profileRepository.findByUsername(username);
        if (profile.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Profile profileData = profile.get();
        return User.withUsername(profileData.getName())
                .password(profileData.getPassword())
                .roles(profileData.getRole().name())
                .build();
    }
}
