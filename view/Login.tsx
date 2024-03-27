import React, { useRef, useState } from 'react';
import {
  IonCard,
  IonCardHeader,
  IonCardTitle,
  IonCardContent,
  IonInput,
  IonButton,
  IonContent,
  IonPage,
} from '@ionic/react';
import { useHistory } from 'react-router';
const Login: React.FC = () => {
  const [email, setEmail] = useState('test@gmail.com');
  const [loading, setLoading] = useState(false);
  
  const passwordRef = useRef<any>(null)
  const history = useHistory();
  const url = "";

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      setLoading(true);
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ 
          email:email, 
          password:passwordRef.current.value ,
        }),
      });

      console.log(response);
      if (response.ok) {
        localStorage.removeItem("error");
        history.push('/mesAnnonces');
      } else {
        localStorage.setItem("error","Mot de passe ou email invalide");
        history.push('/Login');
      }
    } catch (error) {
      console.error('Error during login:', error);
    }finally{
      setLoading(false);
    }
  };

  return (
    <>
    <IonPage>
      <IonContent>
        <IonCard>
          <IonCardHeader className='header'>
            <IonCardTitle class="card-title">Login</IonCardTitle>
          </IonCardHeader>
          <IonCardContent>
            <form onSubmit={handleSubmit}>
              <IonInput
                label='email'
                labelPlacement='floating'
                fill='solid'
                placeholder='email'
                value={email}
                onIonChange={(e) => setEmail(e.detail.value!)}
              ></IonInput>
              <IonInput
                label='password'
                type='password'
                labelPlacement='floating'
                fill='solid'
                placeholder='password'
                value={"test"}
                ref={passwordRef}
              ></IonInput>
              <IonButton type='submit' expand='block' fill='solid' color='primary'>
              {loading ? "En cours..." : "Se connecter"}
              </IonButton>
            </form>
            <p id="error">{localStorage.getItem("error") ? "email or password invalid" :"..."}</p>
          </IonCardContent>
        </IonCard>
      </IonContent>
    </IonPage>
    </>
  );
};
export default Login;
