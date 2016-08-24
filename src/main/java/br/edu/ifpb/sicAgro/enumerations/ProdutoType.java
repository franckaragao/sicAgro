package br.edu.ifpb.sicAgro.enumerations;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public enum ProdutoType {
	
	SEMENTES("Sementes"),
	LEITE("Leite"),
	RACAO_ANIMAL("Ração Animal"),
	MUDAS("Mudas"),
	RECURSO_PLANTACAO("Recurso agricula"),
	RECURSO_CRIACAO("Recurso agropecuário"),
	OUTRO("Outro");
	
	private final String type;

	ProdutoType(String type) {
        this.type = type;
    }

	/**
	 * 
	 * @return
	 */
    public String getType() {
        return type;
    }
}
