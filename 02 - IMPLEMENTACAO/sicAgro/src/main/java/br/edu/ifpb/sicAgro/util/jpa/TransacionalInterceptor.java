package br.edu.ifpb.sicAgro.util.jpa;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Classe utilitária para controle das transções do jpa
 * 
 * Créditos: algaworks (ebook javaEE) www.algaworks.com.br
 *
 */
@Interceptor
@Transactional
public class TransacionalInterceptor {

	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction trx = manager.getTransaction();
		boolean criador = false;

		try {
			if (!trx.isActive()) {
				// truque para fazer rollback no que já passou
				// (senão, um futuro commit, confirmaria até mesmo operações sem transação)
				trx.begin();
				trx.rollback();
				
				// agora sim inicia a transação
				trx.begin();
				
				criador = true;
			}

			return context.proceed();
		} catch (Exception e) {
			if (trx != null && criador) {
				trx.rollback();
			}

			throw e;
		} finally {
			if (trx != null && trx.isActive() && criador) {
				trx.commit();
			}
		}
	}
}
