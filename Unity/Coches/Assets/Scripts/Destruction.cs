using UnityEngine;

public class Destruction : MonoBehaviour
{
    // Tiempo de retraso antes de destruir el objeto
    public float destroyDelay = 1f;

    // Se llama cuando este objeto colisiona con otro
    private void OnCollisionEnter(Collision collision)
    {
        // Asegúrate de que la lógica es adecuada para tus prefabs
        // Por ejemplo, puedes verificar el nombre o una etiqueta del objeto colisionado
        if (collision.gameObject.CompareTag("Destroyable")) 
        {
            // Destruye el objeto actual después del retraso
            //Destroy(gameObject, destroyDelay);
            
        }
    }
}