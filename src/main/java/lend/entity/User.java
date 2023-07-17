package lend.entity;

import jakarta.persistence.*;
import lend.security.Role;
import lend.security.Token;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "_USER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "MAIL", nullable = false, unique = true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "lendUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Book> lentBooks = new ArrayList<>();
	
	@OneToMany(mappedBy = "borrowedUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Book> borrowedBooks = new ArrayList<>();
	
	@OneToMany(mappedBy = "user") private List<Token> tokens;
	  
	@Override public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getAuthorities();
	}
	  
	@Override public String getPassword() { return password; }
	  
	@Override public String getUsername() { return email; }
	  
	@Override public boolean isAccountNonExpired() { return true; }
	  
	@Override public boolean isAccountNonLocked() { return true; }
	  
	@Override public boolean isCredentialsNonExpired() { return true; }
	  
	@Override public boolean isEnabled() { return true; }
	 
}
