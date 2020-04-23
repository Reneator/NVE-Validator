extends PanelContainer

onready var label = $HBoxContainer/VBoxContainer/ResponseContainer/Label


func _on_LineEdit_text_entered(new_text):
	validate_nve(new_text)

func validate_nve(nve):
	#check if number
	pass

func print_error_text(text, error):
	label.text = text
	if error:
		label.modulate = Color.red
	else:
		label.modulate = Color.green
