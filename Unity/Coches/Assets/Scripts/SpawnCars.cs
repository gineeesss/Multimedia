using System.Collections;
using System.Collections.Generic;
using System.Security.Cryptography;
using UnityEngine;

public class SpawnCars : MonoBehaviour
{
    private float elapsedTime = 0;
    private float respawnTime = 1.0f;
    private GameObject enemy;
    private List<GameObject> enemies;
    private string[] carPrefabs = new string[] { "Veh_Van_Green_Z", "Veh_Ute_Red_Z", "Veh_Car_Blue_Z" };

    // Start is called before the first frame update
    void Start()
    {
        enemies = new List<GameObject>();
        SpawnCar();
        //elapsedTime = 0;
    }

    // Update is called once per frame
    void Update()
    {
        elapsedTime += Time.deltaTime;
        if (elapsedTime > respawnTime)
        {
            SpawnCar();
            elapsedTime = 0;
        }
        foreach (var item in enemies)
        {
            if (item.transform.position.z < -16.0f)
            {
                Destroy(item);
                enemies.Remove(item);
            }
        }
    }

    private void SpawnCar()
    {
        GameObject carPrefab = Resources.Load(carPrefabs[Random.Range(0,3)]) as GameObject;
        enemy = Instantiate(carPrefab, new Vector3(Random.Range(-21f, 21f), 0, 360.0f),
            transform.rotation * Quaternion.Euler(0f, 180f, 0f));
        enemy.AddComponent<NpcCar>();
        enemy.GetComponent<NpcCar>().speed = Random.Range(10f, 50f);
        enemies.Add(enemy);
    }
}
