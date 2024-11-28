using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UIElements;

public class UiEvents : MonoBehaviour
{
    private UIDocument _document;
    private Button _button;
    
    // Start is called before the first frame update
    void Start()
    {
        _document = GetComponent<UIDocument>();
        _button = _document.rootVisualElement.Q<Button>("StartButton");
        _button.RegisterCallback<ClickEvent>(OnStartGame);
    }

    private void OnStartGame(ClickEvent evt)
    {
        SceneManager.LoadScene("MainScene");

    }


    // Update is called once per frame
    void Update()
    {
        
    }
}
