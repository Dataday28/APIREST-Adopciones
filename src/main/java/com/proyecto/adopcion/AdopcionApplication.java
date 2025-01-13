package com.proyecto.adopcion;

import com.proyecto.adopcion.persistence.entity.PermissionEntity;
import com.proyecto.adopcion.persistence.entity.RoleEntity;
import com.proyecto.adopcion.persistence.entity.RoleEnum;
import com.proyecto.adopcion.persistence.entity.UserEntity;
import com.proyecto.adopcion.persistence.repository.UserEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AdopcionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdopcionApplication.class, args);
	}



	@Bean
	CommandLineRunner init(UserEntityRepository userEntityRepository) {
		return args -> {
			//CREATE PERMISSIONS
			PermissionEntity createPermission = PermissionEntity.builder().name("CREATE").build();

			PermissionEntity readPermission = PermissionEntity.builder().name("READ").build();

			PermissionEntity updatePermission = PermissionEntity.builder().name("UPDATE").build();

			PermissionEntity deletePermission = PermissionEntity.builder().name("DELETE").build();

			//CREATE ROLES
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission, updatePermission))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission))
					.build();

			//CREATE USERS
			UserEntity userAdmin = UserEntity.builder()
					.username("Admin")
					.password("admin1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userUser = UserEntity.builder()
					.username("User")
					.password("user1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserEntity userInvited = UserEntity.builder()
					.username("Invited")
					.password("invited1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			UserEntity userDeveloper = UserEntity.builder()
					.username("Developer")
					.password("developer1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userEntityRepository.saveAll(List.of(userAdmin, userUser, userInvited, userDeveloper));

		};
	}

}
