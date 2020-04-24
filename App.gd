extends PanelContainer

onready var label = $VBoxContainer/Response_Label

export (PackedScene) var nve_entity_scene

var numbers = ["0","1","2","3","4","5","6","7","8","9"]

# NVE in this script is always without check_digit
func validate_nve(nve: String, check_digit: String):
	clear_error_text()
	var gen_check_digit
	
	if not nve or nve == "":
		print_error_text("Bitte geben sie eine NVE mit oder ohne Prüfziffer ein!")
		return
	if not nve.begins_with("00"):
		print_error_text("Die NVE muss mit '00' als Datenbezeichner anfangen!")
		return
	if not check_number(nve):
		print_error_text("Die NVE darf nur Nummern enthalten!")
		return
	if not nve.length() == 19:
		print_error_text("Die Gesamtlänge der NVE ohne Prüfsumme muss 19 betragen. Aktuell: " + str(nve.length()))
		return
	if check_digit:
		if not check_number(check_digit):
			print_error_text("Die Prüfziffer muss eine Nummer sein!")
			return
		if not check_check_digit(nve, check_digit):
			print_error_text("Die angegebene Prüfziffer ist falsch! Um eine korrekte Prüfziffer berechnen zu lassen, entfernen Sie diese bitte!")
			return
	else:
		gen_check_digit = calculate_check_digit(nve)
		
	
	if gen_check_digit:
		create_nve_entity(nve, gen_check_digit)
	else:
		create_nve_entity(nve, check_digit)
	

func check_number(nve):
	for chara in nve:
		if not numbers.has(chara):
			print(chara)
			return false
	return true
	
func check_check_digit(nve, check_digit): #checks if the given check-digit is correct
	var generated_check_digit = calculate_check_digit(nve)
	return int(check_digit) == generated_check_digit

func calculate_check_digit(nve: String):
	var number_string = nve.substr(2) #removing the 00 
	var position = 1
	var sum = 0
	for chara in number_string:
		position += 1
		var number : int = int(chara)
		if is_even(position):
			sum += number*3
		else:
			sum += number*1
	var check_digit_opposed = sum % 10
	var check_digit
	
	if check_digit_opposed == 0:
		check_digit = 0
	else:
		check_digit = 10 - check_digit_opposed
		
	return check_digit
	
func is_even(number):
	var mod = number % 2
	return mod == 0

func create_nve_entity(nve, check_digit):
	var string = str(nve) + str(check_digit)
	var entity = nve_entity_scene.instance()
	entity.set_nve(string)
	$VBoxContainer/PanelContainer/Entity_List.add_child(entity)
	
	
func print_error_text(text, error = true):
	label.text = text
	if error:
		label.modulate = Color.red
	else:
		label.modulate = Color.green

func clear_error_text():
	label.text = ""


func _on_LineEdit2_text_entered(new_text):
	check_input()

func _on_Button_pressed():
	check_input()

func _on_LineEdit_text_entered(new_text):
	check_input()

func check_input():
	var nve = $VBoxContainer/HBoxContainer/LineEdit.text
	var check_digit = $VBoxContainer/HBoxContainer/LineEdit2.text
	validate_nve(nve, check_digit)
