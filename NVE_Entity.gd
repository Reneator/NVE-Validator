extends HBoxContainer

func set_nve(nve):
	$Label.text = str(nve)

func _on_Button_pressed():
	queue_free()
