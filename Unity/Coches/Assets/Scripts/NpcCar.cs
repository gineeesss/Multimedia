using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NpcCar : MonoBehaviour
{
    // Start is called before the first frame update
    public float speed = 20f;
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        transform.Translate(Vector3.forward * (Time.deltaTime * speed));
    }
    private void OnCollisionEnter(Collision collision)
    {
        // Asegúrate de que la lógica es adecuada para tus prefabs
        // Por ejemplo, puedes verificar el nombre o una etiqueta del objeto colisionado
        if (collision.gameObject.CompareTag("Parador")) 
        {
            // Destruye el objeto actual después del retraso
            //Destroy(gameObject, destroyDelay);
            speed = 0f;
        }
    }
}
