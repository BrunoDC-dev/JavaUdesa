#code for breaking rsa algorithm
from qiskit.aqua.algorithms import Shor
from qiskit.utils import QuantumInstance
from qiskit import Aer

key = 21
base = 2
backend = Aer.get_backend('qasm_simulator')
quantum_instance = QuantumInstance(backend, shots=1000)

shors = Shor(N=key, a=base, quantum_instance=quantum_instance)

result = shors.run()
print(result['factors'])