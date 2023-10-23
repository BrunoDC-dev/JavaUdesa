from selenium import webdriver
from pynput.keyboard import Key, Controller as KeyboardController
import time

keyboard = KeyboardController()

driver = webdriver.Chrome()

# Open a new tab and navigate to the URL
driver.execute_script("window.open('https://www.mercadopago.com.ar/activities/1?period=other&date_from=2023-06-28&date_to=2023-07-11', '_blank')")
driver.switch_to.window(driver.window_handles[-1])
driver.maximize_window()
input('Apreta enter para continuar: ')
html = driver.page_source
elements = html.find('<span class="andes-money-amount__fraction" aria-hidden="true">')
print ('Iniciando...')
for index in range(len(elements)):
    print(elements[index])
#print (elements)
time.sleep(5)
driver.close()
driver.quit()