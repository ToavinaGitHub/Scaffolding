import React, { useState, useEffect } from "react";
import { IonButton, IonModal,IonPage } from '@ionic/react';

const  VenteBillet: React.FC = () => {
  const url = 'http://localhost:8080/';

  const [loading, setLoading] = useState(true);

  const [count, setCount] = useState(1);

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [show2, setShow2] = useState(false);

  const handleClose2 = () => setShow2(false);
  const handleShow2 = () => setShow2(true);

  const [error, setError] = useState(null);
  const [selectedItem, setSelectedItem] =  useState<any>(null);
  const handleSelectItem = (itemKey : any) => {
    handleShow2();
    const itemDetails = vente_billet.find((item: { idVenteBillet: any; }) => item.idVenteBillet === itemKey);
    setSelectedItem(itemDetails);
  };

 	const [vente_billet, setVente_billet] = useState([]);
	
	const [tarif, setTarif] = useState([]);
	
	const [event, setEvent] = useState([]);
	
	

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 3; 
  const totalPageCount = Math.ceil(count / itemsPerPage);

  //////////// SAVE
  const handleSaveSubmit = async (event : any) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const data :any = {};

    for (let [key, value] of formData.entries()) {
      if (form.elements[key].tagName === 'SELECT') {
        data[key] = { id: value };
      } else {
        data[key] = value;
      }
    }

    try {
      const response = await fetch(url + 'vente_billet', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
          'Content-Type': 'application/json'
        }
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      handleClose();
      // If you want to reload the page after success
      window.location.reload();
    } catch (error : any) {
      console.log('Error:', error);
    }
  };

  //////////// UPDATE
  const handleUpdateSubmit = async (event : any) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const data :any = {};
    for (let [key, value] of formData.entries()) {
      if (form.elements[key].tagName === 'SELECT') {
        data[key] = { id : value };
      } else {
        data[key] = value;
      }
    }
    try {
      const response = await fetch(url + 'vente_billet', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      handleClose2();
      // If you want to reload the page after success
      window.location.reload();
    } catch (error) {
      console.error('Error:', error);
    }
  };

  //////////// DELETE
  const handleDeleteClick = async (item : any) => {
    try {
      console.log(item);
      const response = await fetch(url + 'vente_billet', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(item)
      });
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      // If you want to reload the page after success
      window.location.reload();
    } catch (error) {
      console.error('Error:', error);
    }
  };

    	const handleInputPlacesChange = (event :any) => {
		setSelectedItem({ ...selectedItem, places: event.target.value });
	};
	
	const handleInputIdVenteBilletChange = (event :any) => {
		setSelectedItem({ ...selectedItem, idVenteBillet: event.target.value });
	};
	
	const handleInputPrixChange = (event :any) => {
		setSelectedItem({ ...selectedItem, prix: event.target.value });
	};
	
	const handleInputDateVenteChange = (event :any) => {
		setSelectedItem({ ...selectedItem, dateVente: event.target.value });
	};
	
	const handleSelectTarifChange = (event : any) => {
		setSelectedItem({ ...selectedItem, tarif: event.target.value });
	};
	
	const handleInputMontantChange = (event :any) => {
		setSelectedItem({ ...selectedItem, montant: event.target.value });
	};
	
	const handleSelectEventChange = (event : any) => {
		setSelectedItem({ ...selectedItem, event: event.target.value });
	};
	
	const handleInputEtatChange = (event :any) => {
		setSelectedItem({ ...selectedItem, etat: event.target.value });
	};
	
	const handleInputNombreChange = (event :any) => {
		setSelectedItem({ ...selectedItem, nombre: event.target.value });
	};
	
	

    	useEffect(() => {
		const getVente_billet = async () => {
			try {
				const response = await fetch(url + 'vente_billet?page='+ (currentPage - 1));
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setVente_billet(data.content);
			setCount(data.totalElements)} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getVente_billet();
	}, [currentPage]);
	useEffect(() => {
		const getTarif = async () => {
			try {
				const response = await fetch(url + 'tarif');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setTarif(data);
			#count#} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getTarif();
	}, []);
	useEffect(() => {
		const getEvent = async () => {
			try {
				const response = await fetch(url + 'event');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setEvent(data);
			#count#} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getEvent();
	}, []);
	
  return (
    <IonPage>
      <div className="container">
        <div className="row justify-content-end">
          <div className="col">
            <div className="row">
              <IonButton onClick={handleShow}>
                Add VenteBillet
              </IonButton>
            </div>

            <IonModal isOpen={show} onDidDismiss={handleClose}>
              <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
                	<div className="mb-3"> 
	 	<label className="form-label">Places</label> 
	 	<input className="form-control" type="text" name="places" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Prix</label> 
	 	<input className="form-control" type="number" name="prix" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Date Vente</label> 
	 	<input className="form-control" type="date-time local" name="dateVente" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idVenteBillet</label> 
	 	<select className="form-control" name="tarif" id="select-tarif">
			{tarif.map((elt :any) => (
				<option value={elt.idTarif}>{elt.idTarif}</option>
			))}
			
		</select>
	</div><div className="mb-3"> 
	 	<label className="form-label">Montant</label> 
	 	<input className="form-control" type="number" name="montant" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idVenteBillet</label> 
	 	<select className="form-control" name="event" id="select-event">
			{event.map((elt :any) => (
				<option value={elt.idTarif}>{elt.idTarif}</option>
			))}
			
		</select>
	</div><div className="mb-3"> 
	 	<label className="form-label">Etat</label> 
	 	<input className="form-control" type="number" name="etat" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Nombre</label> 
	 	<input className="form-control" type="number" name="nombre" />
	</div>
	
                <IonButton type="submit" >
                  Save Changes
                </IonButton>
              </form>
            </IonModal>
          </div>
        </div>
        <div className="row">
          <table className="table">
            <thead id="table-head">
              <tr>
                			<th> Places </th>
			<th> Id Vente Billet </th>
			<th> Prix </th>
			<th> Date Vente </th>
			<th> Id Tarif </th>
			<th> Montant </th>
			<th> Id Event </th>
			<th> Etat </th>
			<th> Nombre </th>

                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody id="table-body">
              {vente_billet.map((item :any) => (
                <tr key={item.idVenteBillet}>
                  		<td>{item.places}</td>
		<td>{item.idVenteBillet}</td>
		<td>{item.prix}</td>
		<td>{item.dateVente}</td>
		<td>{item.tarif.idTarif}</td>
		<td>{item.montant}</td>
		<td>{item.event.idTarif}</td>
		<td>{item.etat}</td>
		<td>{item.nombre}</td>

                  <td>
                    <IonButton onClick={() => handleDeleteClick(item)}>
                      Delete
                    </IonButton>
                  </td>
                  <td>
                    <IonButton onClick={() => handleSelectItem(item.idVenteBillet)}>
                      Update
                    </IonButton>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <IonModal isOpen={show2} onDidDismiss={handleClose2}>
            <form action="" method="" id="update" onSubmit={handleUpdateSubmit}>
              	<div className="mb-3"> 
	 	<label className="form-label">Places</label> 
	 	<input className="form-control" type="text" name="places" onChange={handleInputPlacesChange} value={selectedItem ? selectedItem.places:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="idVenteBillet" onChange={handleInputIdVenteBilletChange} value={selectedItem ? selectedItem.idVenteBillet:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Prix</label> 
	 	<input className="form-control" type="number" name="prix" onChange={handleInputPrixChange} value={selectedItem ? selectedItem.prix:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Date Vente</label> 
	 	<input className="form-control" type="date-time local" name="dateVente" onChange={handleInputDateVenteChange} value={selectedItem ? selectedItem.dateVente:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idVenteBillet</label> 
	 	<select className="form-control" name="tarif">
			{tarif.map((elt : any) => (
		<option value={elt.idTarif}>{elt.idTarif}</option>
	))}
	
	{event.map((elt : any) => (
		<option value={elt.idTarif}>{elt.idTarif}</option>
	))}
	
	
	</select>
	</div><div className="mb-3"> 
	 	<label className="form-label">Montant</label> 
	 	<input className="form-control" type="number" name="montant" onChange={handleInputMontantChange} value={selectedItem ? selectedItem.montant:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idVenteBillet</label> 
	 	<select className="form-control" name="event">
			{tarif.map((elt : any) => (
		<option value={elt.idTarif}>{elt.idTarif}</option>
	))}
	
	{event.map((elt : any) => (
		<option value={elt.idTarif}>{elt.idTarif}</option>
	))}
	
	
	</select>
	</div><div className="mb-3"> 
	 	<label className="form-label">Etat</label> 
	 	<input className="form-control" type="number" name="etat" onChange={handleInputEtatChange} value={selectedItem ? selectedItem.etat:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Nombre</label> 
	 	<input className="form-control" type="number" name="nombre" onChange={handleInputNombreChange} value={selectedItem ? selectedItem.nombre:''} />
	</div>
	
              <IonButton type="submit">
                Save Changes
              </IonButton>
            </form>
          </IonModal>
        </div>
        {/* Pagination */ }
        <nav aria-label="Page navigation">
        <ul className="pagination justify-content-center">
          <li className={`page-item ${currentPage === 1 ? 'disabled' : ''}`}>
            <button className="page-link" onClick={() => setCurrentPage(prevPage => prevPage - 1)} disabled={currentPage === 1}>Previous</button>
          </li>
          {[...Array(totalPageCount)].map((_, index) => (
            <li key={index} className={`page-item ${currentPage === index + 1 ? 'active' : ''}`}>
              <button className="page-link" onClick={() => setCurrentPage(index + 1)}>{index + 1}</button>
            </li>
          ))}
          <li className={`page-item ${currentPage === totalPageCount+1 ? 'disabled' : ''}`}>
            <button className="page-link" onClick={() => setCurrentPage(prevPage => prevPage + 1)} disabled={currentPage === totalPageCount+1}>Next</button>
          </li>
        </ul>
      </nav>
      </div>
    </IonPage>
  )
}

export default  VenteBillet;
