package br.com.wnfasolutions.veterinarian.mapper;

import br.com.wnfasolutions.veterinarian.dto.request.RoleRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.request.RoleRequestDTO.RoleRequestDTOBuilder;
import br.com.wnfasolutions.veterinarian.dto.request.VeterinarianRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.VeterinarianResponseDTO;
import br.com.wnfasolutions.veterinarian.dto.response.VeterinarianResponseDTO.VeterinarianResponseDTOBuilder;
import br.com.wnfasolutions.veterinarian.entity.RoleDO;
import br.com.wnfasolutions.veterinarian.entity.RoleDO.RoleDOBuilder;
import br.com.wnfasolutions.veterinarian.entity.VeterinarianDO;
import br.com.wnfasolutions.veterinarian.entity.VeterinarianDO.VeterinarianDOBuilder;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-21T06:47:01-0300",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
public class VeterinarianMapperImpl implements VeterinarianMapper {

    @Override
    public VeterinarianDO toModel(VeterinarianRequestDTO veterinarianRequestDTO) {
        if ( veterinarianRequestDTO == null ) {
            return null;
        }

        VeterinarianDOBuilder veterinarianDO = VeterinarianDO.builder();

        veterinarianDO.birthDate( veterinarianRequestDTO.getBirthDate() );
        veterinarianDO.cpf( veterinarianRequestDTO.getCpf() );
        veterinarianDO.crmv( veterinarianRequestDTO.getCrmv() );
        veterinarianDO.email( veterinarianRequestDTO.getEmail() );
        veterinarianDO.firstName( veterinarianRequestDTO.getFirstName() );
        veterinarianDO.lastName( veterinarianRequestDTO.getLastName() );
        veterinarianDO.password( veterinarianRequestDTO.getPassword() );
        veterinarianDO.roles( roleRequestDTOSetToRoleDOSet( veterinarianRequestDTO.getRoles() ) );
        veterinarianDO.username( veterinarianRequestDTO.getUsername() );

        return veterinarianDO.build();
    }

    @Override
    public VeterinarianResponseDTO toResponseDTO(VeterinarianDO veterinarianDO) {
        if ( veterinarianDO == null ) {
            return null;
        }

        VeterinarianResponseDTOBuilder veterinarianResponseDTO = VeterinarianResponseDTO.builder();

        veterinarianResponseDTO.birthDate( veterinarianDO.getBirthDate() );
        veterinarianResponseDTO.cpf( veterinarianDO.getCpf() );
        veterinarianResponseDTO.crmv( veterinarianDO.getCrmv() );
        veterinarianResponseDTO.email( veterinarianDO.getEmail() );
        veterinarianResponseDTO.firstName( veterinarianDO.getFirstName() );
        veterinarianResponseDTO.id( veterinarianDO.getId() );
        veterinarianResponseDTO.lastName( veterinarianDO.getLastName() );
        veterinarianResponseDTO.roles( roleDOSetToRoleRequestDTOSet( veterinarianDO.getRoles() ) );
        veterinarianResponseDTO.situation( veterinarianDO.getSituation() );
        veterinarianResponseDTO.username( veterinarianDO.getUsername() );

        return veterinarianResponseDTO.build();
    }

    protected RoleDO roleRequestDTOToRoleDO(RoleRequestDTO roleRequestDTO) {
        if ( roleRequestDTO == null ) {
            return null;
        }

        RoleDOBuilder roleDO = RoleDO.builder();

        roleDO.roleName( roleRequestDTO.getRoleName() );

        return roleDO.build();
    }

    protected Set<RoleDO> roleRequestDTOSetToRoleDOSet(Set<RoleRequestDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDO> set1 = new HashSet<RoleDO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleRequestDTO roleRequestDTO : set ) {
            set1.add( roleRequestDTOToRoleDO( roleRequestDTO ) );
        }

        return set1;
    }

    protected RoleRequestDTO roleDOToRoleRequestDTO(RoleDO roleDO) {
        if ( roleDO == null ) {
            return null;
        }

        RoleRequestDTOBuilder roleRequestDTO = RoleRequestDTO.builder();

        roleRequestDTO.roleName( roleDO.getRoleName() );

        return roleRequestDTO.build();
    }

    protected Set<RoleRequestDTO> roleDOSetToRoleRequestDTOSet(Set<RoleDO> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleRequestDTO> set1 = new HashSet<RoleRequestDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDO roleDO : set ) {
            set1.add( roleDOToRoleRequestDTO( roleDO ) );
        }

        return set1;
    }
}
