import React, { useState, useEffect } from "react";
import { IonButton, IonModal,IonPage } from '@ionic/react';

const  PrixProduit: React.FC = () => {
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
    const itemDetails = prix_produit.find((item: { idPrixProduit: any; }) => item.idPrixProduit === itemKey);
    setSelectedItem(itemDetails);
  };

 	const [prix_produit, setPrix_produit] = useState([]);
	
	const [produit, setProduit] = useState([]);
	
	

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
      const response = await fetch(url + 'prix_produit', {
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
      const response = await fetch(url + 'prix_produit', {
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
      const response = await fetch(url + 'prix_produit', {
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

    	const handleInputPrixChange = (event :any) => {
		setSelectedItem({ ...selectedItem, prix: event.target.value });
	};
	
	const handleInputDatyChange = (event :any) => {
		setSelectedItem({ ...selectedItem, daty: event.target.value });
	};
	
	const handleInputEtatChange = (event :any) => {
		setSelectedItem({ ...selectedItem, etat: event.target.value });
	};
	
	const handleSelectProduitChange = (event : any) => {
		setSelectedItem({ ...selectedItem, produit: event.target.value });
	};
	
	const handleInputIdPrixProduitChange = (event :any) => {
		setSelectedItem({ ...selectedItem, idPrixProduit: event.target.value });
	};
	
	

    	useEffect(() => {
		const getPrix_produit = async () => {
			try {
				const response = await fetch(url + 'prix_produit?page='+ (currentPage - 1));
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setPrix_produit(data.content);
			setCount(data.totalElements)} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getPrix_produit();
	}, [currentPage]);
	useEffect(() => {
		const getProduit = async () => {
			try {
				const response = await fetch(url + 'produit');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setProduit(data);
			#count#} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getProduit();
	}, []);
	
  return (
    <IonPage>
      <div className="container">
        <div className="row justify-content-end">
          <div className="col">
            <div className="row">
              <IonButton onClick={handleShow}>
                Add PrixProduit
              </IonButton>
            </div>

            <IonModal isOpen={show} onDidDismiss={handleClose}>
              <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
                	<div className="mb-3"> 
	 	<label className="form-label">Prix</label> 
	 	<input className="form-control" type="number" name="prix" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Daty</label> 
	 	<input className="form-control" type="date-time local" name="daty" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Etat</label> 
	 	<input className="form-control" type="number" name="etat" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idPrixProduit</label> 
	 	<select className="form-control" name="produit" id="select-produit">
			{produit.map((elt :any) => (
				<option value={elt.idProduit}>{elt.libelle}</option>
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
                			<th> Prix </th>
			<th> Daty </th>
			<th> Etat </th>
			<th> Id Produit </th>
			<th> Id Prix Produit </th>

                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody id="table-body">
              {prix_produit.map((item :any) => (
                <tr key={item.idPrixProduit}>
                  		<td>{item.prix}</td>
		<td>{item.daty}</td>
		<td>{item.etat}</td>
		<td>{item.produit.libelle}</td>
		<td>{item.idPrixProduit}</td>

                  <td>
                    <IonButton onClick={() => handleDeleteClick(item)}>
                      Delete
                    </IonButton>
                  </td>
                  <td>
                    <IonButton onClick={() => handleSelectItem(item.idPrixProduit)}>
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
	 	<label className="form-label">Prix</label> 
	 	<input className="form-control" type="number" name="prix" onChange={handleInputPrixChange} value={selectedItem ? selectedItem.prix:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Daty</label> 
	 	<input className="form-control" type="date-time local" name="daty" onChange={handleInputDatyChange} value={selectedItem ? selectedItem.daty:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Etat</label> 
	 	<input className="form-control" type="number" name="etat" onChange={handleInputEtatChange} value={selectedItem ? selectedItem.etat:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idPrixProduit</label> 
	 	<select className="form-control" name="produit">
			{produit.map((elt : any) => (
		<option value={elt.idProduit}>{elt.libelle}</option>
	))}
	
	
	</select>
	</div><div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="idPrixProduit" onChange={handleInputIdPrixProduitChange} value={selectedItem ? selectedItem.idPrixProduit:''} />
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

export default  PrixProduit;
