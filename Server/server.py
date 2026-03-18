import socket
import threading

HOST = '0.0.0.0'  
PORT = 12345     

def handle_client(conn, addr):
    print(f"[NOUA CONEXIUNE] Clientul {addr} s-a conectat.")
    connected = True
    
    while connected:
        try:
            # Așteptăm mesajul de la client (Android)
            # Aici implementăm tipul de comunicație "request-response"
            msg = conn.recv(1024).decode('utf-8')
            if not msg:
                break
            
            # Curățăm mesajul de eventuale spații sau caractere noi la final
            msg = msg.strip()
            print(f"[MESAJ PRIMIT] {msg}")
            
            # BIFĂM CERINȚA: string, delimitator, metoda split...
            # Așteptăm un mesaj de forma: COMANDA|Param1|Param2...
            parts = msg.split('|')
            command = parts[0]

            if command == "ADD_INVOICE":
                # Extragem datele facturii folosind delimitatorul "|"
                # Format așteptat: ADD_INVOICE|Nume Client|Suma|Data|Descriere|Status
                if len(parts) >= 6:
                    client_name = parts[1]
                    amount = parts[2]
                    print(f"[SERVER] Procesez factura pentru: {client_name}, suma: {amount} RON")
                    
                    # Trimitem un response înapoi către Android
                    response = "SUCCESS|Factura a fost inregistrata cu succes!\n"
                    conn.send(response.encode('utf-8'))
                else:
                    conn.send("ERROR|Date incomplete pentru adaugarea facturii.\n".encode('utf-8'))
                    
            elif command == "DISCONNECT":
                connected = False
                conn.send("SUCCESS|Deconectare reusita.\n".encode('utf-8'))
            else:
                conn.send("ERROR|Comanda necunoscuta.\n".encode('utf-8'))
                
        except Exception as e:
            print(f"[EROARE] Conexiune întreruptă: {e}")
            break
            
    conn.close()
    print(f"[DECONECTAT] Clientul {addr} a fost deconectat.")

def start_server():
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.bind((HOST, PORT))
    server.listen()
    print(f"[START] Serverul TCP ascultă pe {HOST}:{PORT}")
    
    while True:
        # Așteptăm conexiuni noi
        conn, addr = server.accept()
        # Creăm un thread nou pentru fiecare client (notificările necesită thread-uri separate conform cursului)
        thread = threading.Thread(target=handle_client, args=(conn, addr))
        thread.start()
        print(f"[INFO] Thread-uri active: {threading.active_count() - 1}")

if __name__ == "__main__":
    start_server()