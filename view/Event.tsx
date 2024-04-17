import React, { useState, useEffect } from "react";
import { IonButton, IonModal,IonPage } from '@ionic/react';

const  Event: React.FC = () => {
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
    const itemDetails = event.find((item: { idEvent: any; }) => item.idEvent === itemKey);
    setSelectedItem(itemDetails);
  };

 	const [event, setEvent] = useState([]);
	
	const [film, setFilm] = useState([]);
	
	const [salle, setSalle] = useState([]);
	
	

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
      const response = await fetch(url + 'event', {
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
      const response = await fetch(url + 'event', {
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
      const response = await fetch(url + 'event', {
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

    	const handleInputDateChange = (event :any) => {
		setSelectedItem({ ...selectedItem, date: event.target.value });
	};
	
	const handleInputHeureChange = (event :any) => {
		setSelectedItem({ ...selectedItem, heure: event.target.value });
	};
	
	const handleInputPrixChange = (event :any) => {
		setSelectedItem({ ...selectedItem, prix: event.target.value });
	};
	
	const handleSelectFilmChange = (event : any) => {
		setSelectedItem({ ...selectedItem, film: event.target.value });
	};
	
	const handleSelectSalleChange = (event : any) => {
		setSelectedItem({ ...selectedItem, salle: event.target.value });
	};
	
	const handleInputIdEventChange = (event :any) => {
		setSelectedItem({ ...selectedItem, idEvent: event.target.value });
	};
	
	const handleInputEtatChange = (event :any) => {
		setSelectedItem({ ...selectedItem, etat: event.target.value });
	};
	
	

    	useEffect(() => {
		const getEvent = async () => {
			try {
				const response = await fetch(url + 'event?page='+ (currentPage - 1));
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setEvent(data.content);
			setCount(data.totalElements)} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getEvent();
	}, [currentPage]);
	useEffect(() => {
		const getFilm = async () => {
			try {
				const response = await fetch(url + 'film');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setFilm(data);
			#count#} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getFilm();
	}, []);
	useEffect(() => {
		const getSalle = async () => {
			try {
				const response = await fetch(url + 'salle');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setSalle(data);
			#count#} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getSalle();
	}, []);
	
  return (
    <IonPage>
      <div className="container">
        <div className="row justify-content-end">
          <div className="col">
            <div className="row">
              <IonButton onClick={handleShow}>
                Add Event
              </IonButton>
            </div>

            <IonModal isOpen={show} onDidDismiss={handleClose}>
              <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
                	<div className="mb-3"> 
	 	<label className="form-label">Date</label> 
	 	<input className="form-control" type="date-time local" name="date" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Heure</label> 
	 	<input className="form-control" type="date-time local" name="heure" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Prix</label> 
	 	<input className="form-control" type="number" name="prix" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idEvent</label> 
	 	<select className="form-control" name="film" id="select-film">
			{film.map((elt :any) => (
				<option value={elt.idFilm}>{elt.sary}</option>
			))}
			
		</select>
	</div><div className="mb-3"> 
	 	<label className="form-label">idEvent</label> 
	 	<select className="form-control" name="salle" id="select-salle">
			{salle.map((elt :any) => (
				<option value={elt.idFilm}>{elt.sary}</option>
			))}
			
		</select>
	</div><div className="mb-3"> 
	 	<label className="form-label">Etat</label> 
	 	<input className="form-control" type="number" name="etat" />
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
                			<th> Date </th>
			<th> Heure </th>
			<th> Prix </th>
			<th> Id Film </th>
			<th> Id Salle </th>
			<th> Id Event </th>
			<th> Etat </th>

                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody id="table-body">
              {event.map((item :any) => (
                <tr key={item.idEvent}>
                  		<td>{item.date}</td>
		<td>{item.heure}</td>
		<td>{item.prix}</td>
		<td>{item.film.sary}</td>
		<td>{item.salle.sary}</td>
		<td>{item.idEvent}</td>
		<td>{item.etat}</td>

                  <td>
                    <IonButton onClick={() => handleDeleteClick(item)}>
                      Delete
                    </IonButton>
                  </td>
                  <td>
                    <IonButton onClick={() => handleSelectItem(item.idEvent)}>
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
	 	<label className="form-label">Date</label> 
	 	<input className="form-control" type="date-time local" name="date" onChange={handleInputDateChange} value={selectedItem ? selectedItem.date:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Heure</label> 
	 	<input className="form-control" type="date-time local" name="heure" onChange={handleInputHeureChange} value={selectedItem ? selectedItem.heure:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Prix</label> 
	 	<input className="form-control" type="number" name="prix" onChange={handleInputPrixChange} value={selectedItem ? selectedItem.prix:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idEvent</label> 
	 	<select className="form-control" name="film">
			{film.map((elt : any) => (
		<option value={elt.idFilm}>{elt.sary}</option>
	))}
	
	{salle.map((elt : any) => (
		<option value={elt.idFilm}>{elt.sary}</option>
	))}
	
	
	</select>
	</div><div className="mb-3"> 
	 	<label className="form-label">idEvent</label> 
	 	<select className="form-control" name="salle">
			{film.map((elt : any) => (
		<option value={elt.idFilm}>{elt.sary}</option>
	))}
	
	{salle.map((elt : any) => (
		<option value={elt.idFilm}>{elt.sary}</option>
	))}
	
	
	</select>
	</div><div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="idEvent" onChange={handleInputIdEventChange} value={selectedItem ? selectedItem.idEvent:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Etat</label> 
	 	<input className="form-control" type="number" name="etat" onChange={handleInputEtatChange} value={selectedItem ? selectedItem.etat:''} />
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

export default  Event;
