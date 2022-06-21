package br.com.wnfasolutions.veterinarian.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.veterinarian.dto.request.VeterinarianRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.VeterinarianResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.RoleDO;
import br.com.wnfasolutions.veterinarian.entity.VeterinarianDO;
import br.com.wnfasolutions.veterinarian.enums.Situation;
import br.com.wnfasolutions.veterinarian.exception.ResourceNotFoundException;
import br.com.wnfasolutions.veterinarian.exception.RolesNotFoundException;
import br.com.wnfasolutions.veterinarian.mapper.VeterinarianMapper;
import br.com.wnfasolutions.veterinarian.repository.RoleRepository;
import br.com.wnfasolutions.veterinarian.repository.VeterinarianRepository;
import br.com.wnfasolutions.veterinarian.service.VeterinarianService;

@Service
public class VeterinarianServiceImpl implements VeterinarianService {
	@Autowired
	private VeterinarianRepository veterinarianRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final VeterinarianMapper veterinarianMapper = VeterinarianMapper.INSTANCE;
	
	@Override
	public VeterinarianResponseDTO createNewVeterinarian(VeterinarianRequestDTO veterinarianRequestDTO) throws Exception {		
		VeterinarianDO veterinarianDO = convertToModel(veterinarianRequestDTO);
		veterinarianDO.setSituation(Situation.ATIVO);
		veterinarianDO.setPassword(passwordEncoder.encode(veterinarianRequestDTO.getPassword()));
		veterinarianDO.setRoles(getRoles(veterinarianRequestDTO));
		return convertToResponse(veterinarianRepository.save(veterinarianDO));
	}

	@Override
	public Page<VeterinarianResponseDTO> findAll(Pageable pageable) {
		Page<VeterinarianDO> veterinarios = veterinarianRepository.findAll(pageable);
		return new PageImpl<>(convertToResponse(veterinarios), pageable, veterinarios.getTotalElements());
	}

	@Override
	public VeterinarianDO findById(Long id) throws Exception {
		return verificarSeExiste(id);
	}
	

	@Override
	public VeterinarianResponseDTO updateVeterinarian(Long id, VeterinarianRequestDTO veterinarianRequestDTO)
			throws Exception {
		VeterinarianDO veterinarianDO = verificarSeExiste(id);
		veterinarianDO.setPassword(passwordEncoder.encode(veterinarianRequestDTO.getPassword()));
		veterinarianDO.setRoles(getRoles(veterinarianRequestDTO));
		BeanUtils.copyProperties(veterinarianRequestDTO, veterinarianDO, "id");
		return convertToResponse(veterinarianRepository.save(veterinarianDO));
	}

	@Override
	public VeterinarianResponseDTO findOneById(Long id) throws Exception {
		return convertToResponse(verificarSeExiste(id));
	}
	
	@Override
	public void activateVeterinarian(Long id) throws Exception {
		alterarSituacaoVeterinarian(id, Situation.ATIVO);
	}

	@Override
	public void disableVeterinarian(Long id) throws Exception {
		alterarSituacaoVeterinarian(id, Situation.INATIVO);
	}

	private Set<RoleDO> getRoles(VeterinarianRequestDTO veterinarianRequestDTO) throws Exception {

		Set<RoleDO> roles = veterinarianRequestDTO.getRoles().stream()
				.map(roleName -> roleRepository.findByRoleName(roleName.getRoleName()))
				.filter(role -> role != null)
				.collect(Collectors.toSet());
		if (roles.isEmpty()) {
			throw new RolesNotFoundException();
		}
		return roles;
	}
	
	private void alterarSituacaoVeterinarian(Long id, Situation situation) throws Exception {
		VeterinarianDO veterinarianDO = verificarSeExiste(id);
		veterinarianDO.setSituation(situation);
		veterinarianRepository.save(veterinarianDO);
	}
	
	private VeterinarianDO verificarSeExiste(Long id) throws Exception {
		Optional<VeterinarianDO> veterinarianOptional = veterinarianRepository.findById(id);
		if (veterinarianOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return veterinarianOptional.get();
	}
	
	private VeterinarianDO convertToModel(VeterinarianRequestDTO veterinarianRequestDTO) {
		return veterinarianMapper.toModel(veterinarianRequestDTO);
	}
	
	private VeterinarianResponseDTO convertToResponse(VeterinarianDO veterinatianSaved) {
		return veterinarianMapper.toResponseDTO(veterinatianSaved);
	}
	
	private List<VeterinarianResponseDTO> convertToResponse(Page<VeterinarianDO> veterinarios) {
		return veterinarios.stream()
				.map(veterinarianMapper::toResponseDTO)
				.collect(Collectors.toList());
	}
}
