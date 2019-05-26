package infrastructure.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorDeTransacao  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	@AroundInvoke
	public Object executaTX(InvocationContext contexto) throws Exception  {
		Object resultado = null;
		try {
			em.getTransaction().begin();
			resultado = contexto.proceed();
			em.getTransaction().commit();
			
		} catch(Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		} finally {
			
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			
		}
		
		return resultado;
	}
}

