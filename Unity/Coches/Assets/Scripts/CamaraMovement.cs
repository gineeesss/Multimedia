using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CamaraMovement : MonoBehaviour
{
    // Start is called before the first frame update
    public GameObject mainCar;
    public Vector3 cameraOffset;
    void Start()
    {
        cameraOffset = new Vector3(0,4,0);
    }

    // Update is called once per frame
    void Update()
    {
        transform.position = mainCar.transform.position+new Vector3(0,6,-8);
        
        float hInput = Input.GetAxis("Horizontal");
        transform.Rotate(Vector3.up * (Time.deltaTime * 30f*hInput));
    }
}
