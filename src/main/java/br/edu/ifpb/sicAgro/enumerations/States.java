package br.edu.ifpb.sicAgro.enumerations;

public enum States{
	
    AC(1, "Acre"),
    AL(2, "Alagoas"),
    AM(3, "Amazonas"),
    AP(4, "Amapá"),
    BA(5, "Bahia"),
    CE(6, "Ceará"),
    DF(7, "Distrito Federal"),
    ES(8, "Espírito Santo"),
    GO(9, "Goiás"),
    MA(10, "Maranhão"),
    MG(11, "Minas Gerais"),
    MS(12, "Mato Grosso do Sul"),
    MT(13, "Mato Grosso"),
    PA(14, "Pará"),
    PB(15, "Paraíba"),
    PE(16, "Pernambuco"),
    PI(17, "Piauí"),
    PR(18, "Paraná"),
    RJ(19, "Rio de Janeiro"),
    RN(20, "Rio Grande do Norte"),
    RO(21, "Rondônia"),
    RR(22, "Roraima"),
    RS(23, "Rio Grande do Sul"),
    SC(24, "Santa Catarina"),
    SE(25, "Sergipe"),
    SP(26, "São Paulo"),
    TO(27, "Tocantins");

    private final String descricao;

    private final Integer codigo;

    States(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    /**
     * Permite que a descrição(valor) do item do ENUM seja retornado ao invés da
     * constante(chave).
     *
     * @return descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Associa um código a cada item do enum. O principal uso vai ser passar o
     * código como parâmetro para uma consulta no banco e retornar as cidades de
     * acordo com o estado.
     *
     * @return codigo
     */
    public Integer getCodigo() {
        return codigo;
    }
	
	
	
	

}
