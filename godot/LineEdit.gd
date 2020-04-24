extends LineEdit


# Declare member variables here. Examples:
# var a = 2
# var b = "text"

var init_state = true
export (String) var init_text = "Bitte ihre NVE/SSCC hier eingeben..."

# Called when the node enters the scene tree for the first time.
func _ready():
	pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
#func _process(delta):
#	pass


func _on_LineEdit_text_changed(new_text):
	if text == "" or not text:
		text
	pass # Replace with function body.


func _on_LineEdit_focus_entered():
	if init_state:
		text = ""
		init_state = false

func _on_LineEdit_focus_exited():
	if text == "":
		init_state = true
		text = init_text
	pass # Replace with function body.
