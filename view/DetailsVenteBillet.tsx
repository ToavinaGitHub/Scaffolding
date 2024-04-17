import React, { useState, useEffect } from "react";
import { IonButton, IonModal,IonPage } from '@ionic/react';

const  DetailsVenteBillet: React.FC = () => {
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
    const itemDetails = details_vente_billet.find((item: { idDetailsVenteBillet: any; }) => item.idDetailsVenteBillet === itemKey);
    setSelectedItem(itemDetails);
  };

 	const [details_vente_billet, setDetails_vente_billet] = useState([]);
	
	const [venteBillet, setVenteBillet] = useState([]);
	
	const [placeSalle, setPlaceSalle] = useState([]);
	
	

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
      const response = await fetch(url + 'details_vente_billet', {
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
      const response = await fetch(url + 'details_vente_billet', {
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
      const response = await fetch(url + 'details_vente_billet', {
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

    	const handleSelectVenteBilletChange = (event : any) => {
		setSelectedItem({ ...selectedItem, venteBillet: event.target.value });
	};
	
	const handleInputIdDetailsVenteBilletChange = (event :any) => {
		setSelectedItem({ ...selectedItem, idDetailsVenteBillet: event.target.value });
	};
	
	const handleSelectPlaceSalleChange = (event : any) => {
		setSelectedItem({ ...selectedItem, placeSalle: event.target.value });
	};
	
	

    	useEffect(() => {
		const getDetails_vente_billet = async () => {
			try {
				const response = await fetch(url + 'details_vente_billet?page='+ (currentPage - 1));
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setDetails_vente_billet(data.content);
			setCount(data.totalElements)} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getDetails_vente_billet();
	}, [currentPage]);
	useEffect(() => {
		const getVenteBillet = async () => {
			try {
				const response = await fetch(url + 'venteBillet');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setVenteBillet(data);
			#count#} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getVenteBillet();
	}, []);
	useEffect(() => {
		const getPlaceSalle = async () => {
			try {
				const response = await fetch(url + 'placeSalle');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setPlaceSalle(data);
			#count#} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getPlaceSalle();
	}, []);
	
  return (
    <IonPage>
      <div className="container">
        <div className="row justify-content-end">
          <div className="col">
            <div className="row">
              <IonButton onClick={handleShow}>
                Add DetailsVenteBillet
              </IonButton>
            </div>

            <IonModal isOpen={show} onDidDismiss={handleClose}>
              <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
                	<div className="mb-3"> 
	 	<label className="form-label">idDetailsVenteBillet</label> 
	 	<select className="form-control" name="venteBillet" id="select-venteBillet">
			{venteBillet.map((elt :any) => (
				<option value={elt.idVenteBillet}>{elt.places}</option>
			))}
			
		</select>
	</div><div className="mb-3"> 
	 	<label className="form-label">idDetailsVenteBillet</label> 
	 	<select className="form-control" name="placeSalle" id="select-placeSalle">
			{placeSalle.map((elt :any) => (
				<option value={elt.idVenteBillet}>{elt.places}</option>
			))}
			
		</select>
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
                			<th> Id Vente Billet </th>
			<th> Id Details Vente Billet </th>
			<th> Id Place Salle </th>

                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody id="table-body">
              {details_vente_billet.map((item :any) => (
                <tr key={item.idDetailsVenteBillet}>
                  		<td>{item.venteBillet.places}</td>
		<td>{item.idDetailsVenteBillet}</td>
		<td>{item.placeSalle.places}</td>

                  <td>
                    <IonButton onClick={() => handleDeleteClick(item)}>
                      Delete
                    </IonButton>
                  </td>
                  <td>
                    <IonButton onClick={() => handleSelectItem(item.idDetailsVenteBillet)}>
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
	 	<label className="form-label">idDetailsVenteBillet</label> 
	 	<select className="form-control" name="venteBillet">
			{venteBillet.map((elt : any) => (
		<option value={elt.idVenteBillet}>{elt.places}</option>
	))}
	
	{placeSalle.map((elt : any) => (
		<option value={elt.idVenteBillet}>{elt.places}</option>
	))}
	
	
	</select>
	</div><div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="idDetailsVenteBillet" onChange={handleInputIdDetailsVenteBilletChange} value={selectedItem ? selectedItem.idDetailsVenteBillet:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idDetailsVenteBillet</label> 
	 	<select className="form-control" name="placeSalle">
			{venteBillet.map((elt : any) => (
		<option value={elt.idVenteBillet}>{elt.places}</option>
	))}
	
	{placeSalle.map((elt : any) => (
		<option value={elt.idVenteBillet}>{elt.places}</option>
	))}
	
	
	</select>
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

export default  DetailsVenteBillet;
