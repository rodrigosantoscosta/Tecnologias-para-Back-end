package br.com.iespflix.iespflix.model;


import br.com.iespflix.iespflix.validation.CPFouCNPJ;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nome completo é obrigatório")
    @Size(max = 150, message = "Nome completo deve ter no máximo 150 caracteres")
    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nomeCompleto;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve ser uma data passada")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Size(max = 254, message = "Email deve ter no máximo 254 caracteres")
    @Column(nullable = false, unique = true, length = 254)
    private String email;


    @NotBlank(message = "Senha hash é obrigatória")
    @Size(min = 60, max = 60, message = "Senha hash deve ter exatamente 60 caracteres")
    @Column(name = "senha_hash", nullable = false, length = 60)
    private String senhaHash;

    @CPFouCNPJ(message = "CPF/CNPJ deve ser válido")
    @Column(name = "cpf_cnpj", unique = true, length = 14)
    private String cpfCnpj;

    @NotBlank(message = "Perfil é obrigatório")
    @Column(nullable = false, length = 20)
    private String perfil;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;


}