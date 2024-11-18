using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AplicarMovimiento : MonoBehaviour
{
    // Start is called before the first frame update
    private Rigidbody _rigidbody;
    private Vector3 _movement;
    private float velocidad = 10.0f;
    void Start()
    {
        _rigidbody = GetComponent<Rigidbody>();
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        float movHorizontal = Input.GetAxis("Horizontal");
        float movVertical = Input.GetAxis("Vertical");

        if (Input.GetKey(KeyCode.Space))
        {
            Vector3 salto = new Vector3(0f, 1.0f, 0f);
            _rigidbody.AddForce(salto, ForceMode.Impulse);
        }
        
        _movement = new Vector3(movHorizontal*velocidad,0.0f,movVertical*velocidad);
        _rigidbody.AddForce(_movement);
        
    }
}