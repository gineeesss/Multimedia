using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AplicarFuerza : MonoBehaviour
{
    // Start is called before the first frame update
    private Rigidbody _rigidbody;
    private int _contador = 0;
    void Start()
    {
        Debug.Log("Hola Mundo");
        _rigidbody = GetComponent<Rigidbody>();
    }

    // Update is called once per frame
    void Update()
    {
        Vector2 movimiento = new Vector3(0f,6.0f,0f);
        _rigidbody.AddForce(movimiento);
        Debug.Log("KLK Manin" + _contador++ );
        
    }
}
