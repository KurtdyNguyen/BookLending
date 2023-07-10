package lend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "USERS")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User /*implements UserDetails*/{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String userName;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "MAIL", nullable = false, unique = true)
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();
	
	@OneToMany(mappedBy = "lendUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Book> lentBooks = new ArrayList<>();
	
	@OneToMany(mappedBy = "borrowedUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Book> borrowedBooks = new ArrayList<>();
	
	/*
	 * @OneToMany(mappedBy = "user") private List<Token> tokens;
	 * 
	 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
	 * return roles.getAuthorities(); }
	 * 
	 * @Override public String getPassword() { return password; }
	 * 
	 * @Override public String getUsername() { return email; }
	 * 
	 * @Override public boolean isAccountNonExpired() { return true; }
	 * 
	 * @Override public boolean isAccountNonLocked() { return true; }
	 * 
	 * @Override public boolean isCredentialsNonExpired() { return true; }
	 * 
	 * @Override public boolean isEnabled() { return true; }
	 */
}
