using System.Collections;
using System.Collections.Generic;
using System.Numerics;
using UnityEngine;
using Vector3 = UnityEngine.Vector3;

public class MainCarController : MonoBehaviour
{
    public GameObject mainCar;
    public float speed = 20f;
    public float rotationSpeed = 30f;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        float vInput = Input.GetAxis("Vertical");
        transform.Translate(Vector3.forward * (Time.deltaTime * speed *vInput));
        
        float hInput = Input.GetAxis("Horizontal");
        transform.Rotate(Vector3.up * (Time.deltaTime * rotationSpeed*hInput));
    }
}
