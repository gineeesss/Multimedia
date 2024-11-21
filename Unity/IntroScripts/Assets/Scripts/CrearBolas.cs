using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UIElements;

public class CrearBolas : MonoBehaviour
{
    public GameObject prefab;
    public int numeroBolas = 10;
    private float size = 1.0f;
    private GameObject go ;
    private List<GameObject> bolas = new List<GameObject>();
    private List<Color> colors = new List<Color> { Color.blue, Color.red, Color.green };
    private Time time;

    // Start is called before the first frame update
    void Start()
    {
        for (int i = 0; i < numeroBolas; i++)
        {
            Vector3 posicion= new Vector3(
                Random.Range(-2.5f, 2.5f),
                Random.Range(50, 55),
                Random.Range(-2.5f, 2.5f));
            Quaternion rotacion = Quaternion.identity;
            GameObject go = Instantiate(prefab, posicion, rotacion);
            go.transform.position = posicion;
            size = Random.Range(0.5f, 0.9f);
            go.transform.localScale = new Vector3(size, size, size);

            
            
            MeshRenderer renderer = go.GetComponent<MeshRenderer>();
            Color col = new Color
            (
                Random.Range(0.0f, 1.0f),
                Random.Range(0.0f, 1.0f),
                Random.Range(0.0f, 1.0f)
            );  
            renderer.material.color = col;
            bolas.Add(go);
        }
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        foreach (GameObject go in bolas)
        {
            if (go.transform.localScale.x > 0.5f)
            {
                go.transform.localScale += new Vector3(0.00001f, 0.00001f, 0.00001f);

            }
        }
    }
    private void Update()
    {
        time = time + Time.deltaTime;
        if (time > 2.0f)
        {
            Debug.Log("Tiempo: " + time + " segundos!");
            time = 0;
        }

        foreach (GameObject go in bolas)
        {
            if (go.transform.position.y < -2f)
            {
                Destroy(go);
                bolas.Remove(go);
            }
        }
    }

    private void OnMouseUp()
    {
        Destroy(go);
    }
}
