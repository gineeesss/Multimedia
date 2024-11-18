using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CrearMuro : MonoBehaviour
{
    public GameObject prefabLadrillo;
    public int ladrillos = 5;
    public float altura = 5;
    private List<GameObject> muro = new List<GameObject>();
    private GameObject ladrillo;
    // Start is called before the first frame update
    void Start()
    {   
        for (int i = 1; i <= ladrillos; i++)
        {
            float inicioX = 0f;
            for (float j = 0; j < altura+0.5f; j++)
            {
                Vector3 posicion= new Vector3(
                    inicioX,
                    altura,
                    0
                );  
                Quaternion rotacion = Quaternion.identity;
                GameObject ladrillo = Instantiate(prefabLadrillo, posicion, rotacion);
                ladrillo.transform.position = posicion;
                muro.Add(ladrillo);
            }
        }
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
