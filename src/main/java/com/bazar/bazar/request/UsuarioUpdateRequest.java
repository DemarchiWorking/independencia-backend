package com.bazar.bazar.request;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateRequest {
    private String nome;
    private String cpf;
    private String telefone;
    private String foto;
}