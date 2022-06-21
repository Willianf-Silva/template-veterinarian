package br.com.wnfasolutions.veterinarian.mapper;

import br.com.wnfasolutions.veterinarian.dto.request.RoleRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.RoleResponseDTO;
import br.com.wnfasolutions.veterinarian.dto.response.RoleResponseDTO.RoleResponseDTOBuilder;
import br.com.wnfasolutions.veterinarian.entity.RoleDO;
import br.com.wnfasolutions.veterinarian.entity.RoleDO.RoleDOBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-21T06:33:45-0300",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDO toModel(RoleRequestDTO roleRequestDTO) {
        if ( roleRequestDTO == null ) {
            return null;
        }

        RoleDOBuilder roleDO = RoleDO.builder();

        roleDO.roleName( roleRequestDTO.getRoleName() );

        return roleDO.build();
    }

    @Override
    public RoleResponseDTO toResponseDTO(RoleDO roleDO) {
        if ( roleDO == null ) {
            return null;
        }

        RoleResponseDTOBuilder roleResponseDTO = RoleResponseDTO.builder();

        roleResponseDTO.id( roleDO.getId() );
        roleResponseDTO.roleName( roleDO.getRoleName() );

        return roleResponseDTO.build();
    }
}
