package lend.entity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="ROLE")
@Data
@RequiredArgsConstructor
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @ManyToMany(mappedBy="roles")
    private List<User> users;
    
	/*
	 * public List<SimpleGrantedAuthority> getAuthorities() { var authorities =
	 * getPermissions() .stream() .map(permission -> new
	 * SimpleGrantedAuthority(permission.getPermission()))
	 * .collect(Collectors.toList()); authorities.add(new
	 * SimpleGrantedAuthority("ROLE_" + this.name())); return authorities; }
	 */
}
