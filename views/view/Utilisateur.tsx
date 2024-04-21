import React, { useState, useEffect } from "react";
import { IonButton, IonModal,IonPage,IonIcon,IonAlert } from '@ionic/react';

import { trash,pencil } from "ionicons/icons";
const  Utilisateur: React.FC = () => {
  const url = 'http://localhost:8080/';

  const [loading, setLoading] = useState(true);

  const [count, setCount] = useState(1);

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [show2, setShow2] = useState(false);

  const handleClose2 = () => setShow2(false);
  const handleShow2 = () => setShow2(true);

    const [showConfirmationModal, setShowConfirmationModal] = useState(false);

  const [error, setError] = useState(null);
  const [selectedItem, setSelectedItem] =  useState<any>(null);
  const handleSelectItem = (itemKey : any) => {
    handleShow2();
    const itemDetails = utilisateur.find((item: { idUtilisateur: any; }) => item.idUtilisateur === itemKey);
    setSelectedItem(itemDetails);
  };

 	const [utilisateur, setUtilisateur] = useState([]);
	
	

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
      const response = await fetch(url + 'utilisateur', {
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
      const response = await fetch(url + 'utilisateur', {
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
  const handleDeleteClick = (item: any) => {
    setSelectedItem(item);
    setShowConfirmationModal(true);
  };

  const handleConfirmDelete = async () => {
    try {
      const response = await fetch(url + 'utilisateur', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(selectedItem)
      });
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      setShowConfirmationModal(false);
      // Refresh the data after deletion
      window.location.reload();
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const handleCancelDelete = () => {
    setShowConfirmationModal(false);
  };

    	const handleInputPasswordChange = (event :any) => {
		setSelectedItem({ ...selectedItem, password: event.target.value });
	};
	
	const handleInputIdUtilisateurChange = (event :any) => {
		setSelectedItem({ ...selectedItem, idUtilisateur: event.target.value });
	};
	
	const handleInputNomChange = (event :any) => {
		setSelectedItem({ ...selectedItem, nom: event.target.value });
	};
	
	const handleInputEmailChange = (event :any) => {
		setSelectedItem({ ...selectedItem, email: event.target.value });
	};
	
	

    	useEffect(() => {
		const getUtilisateur = async () => {
			try {
				const response = await fetch(url + 'utilisateur?page='+ (currentPage - 1));
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setUtilisateur(data.content);
			setCount(data.totalElements)} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getUtilisateur();
	}, [currentPage]);
	
  return (
    <IonPage>
      <div className="container">
        <div className="row justify-content-end">
          <div className="col">
            <div className="row">
              <IonButton onClick={handleShow}>
                Add Utilisateur
              </IonButton>
            </div>

            <IonModal isOpen={show} onDidDismiss={handleClose}>
              <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
                	<div className="mb-3"> 
	 	<label className="form-label">password</label> 
	 	<input className="form-control" type="text" name="password" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">nom</label> 
	 	<input className="form-control" type="text" name="nom" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">email</label> 
	 	<input className="form-control" type="text" name="email" />
	</div>
	

                <IonButton color={"danger"} onClick={handleClose}>
                  Close
                </IonButton>
                <IonButton color={"success"} type="submit" >
                  Save 
                </IonButton>
              </form>
            </IonModal>
          </div>
        </div>
        <div className="row">
          <table className="table">
            <thead id="table-head">
              <tr>
                			<th> Password </th>
			<th> Id Utilisateur </th>
			<th> Nom </th>
			<th> Email </th>

                <th></th>
              </tr>
            </thead>
            <tbody id="table-body">
              {utilisateur.map((item :any) => (
                <tr key={item.idUtilisateur}>
                  		<td>{item.password}</td>
		<td>{item.idUtilisateur}</td>
		<td>{item.nom}</td>
		<td>{item.email}</td>

                  <td>
                    <IonButton onClick={() => handleDeleteClick(item)} color={"danger"}>
                      <IonIcon aria-hidden="true" icon={trash} />
                    </IonButton>
                    <IonButton onClick={() => handleSelectItem(item.idUtilisateur)} color={"success"}>
                      <IonIcon aria-hidden="true" icon={pencil} />
                    </IonButton>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <IonModal isOpen={show2} onDidDismiss={handleClose2}>
            <form action="" method="" id="update" onSubmit={handleUpdateSubmit}>
              	<div className="mb-3"> 
	 	<label className="form-label">Password</label> 
	 	<input className="form-control" type="text" name="password" onChange={handleInputPasswordChange} value={selectedItem ? selectedItem.password:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="idUtilisateur" onChange={handleInputIdUtilisateurChange} value={selectedItem ? selectedItem.idUtilisateur:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Nom</label> 
	 	<input className="form-control" type="text" name="nom" onChange={handleInputNomChange} value={selectedItem ? selectedItem.nom:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Email</label> 
	 	<input className="form-control" type="text" name="email" onChange={handleInputEmailChange} value={selectedItem ? selectedItem.email:''} />
	</div>
	

              <IonButton color={"danger"} onClick={handleClose2}>
                  Close
              </IonButton>
              <IonButton color={"success"} type="submit">
                Save Changes
              </IonButton>
            </form>
          </IonModal>
          <IonModal isOpen={showConfirmationModal} onDidDismiss={() => setShowConfirmationModal(false)}>
            <IonAlert
              isOpen={showConfirmationModal}
              onDidDismiss={() => setShowConfirmationModal(false)}
              header={'Confirm Deletion'}
              message={'Are you sure you want to delete this item?'}
              buttons={[
                {
                  text: 'Cancel',
                  role: 'cancel',
                  cssClass: 'secondary',
                  handler: handleCancelDelete
                },
                {
                  text: 'Delete',
                  handler: handleConfirmDelete
                }
              ]}
            />
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
          <li className={`page-item ${currentPage === totalPageCount ? 'disabled' : ''}`}>
            <button className="page-link" onClick={() => setCurrentPage(prevPage => prevPage + 1)} disabled={currentPage === totalPageCount}>Next</button>
          </li>
        </ul>
      </nav>
      </div>
    </IonPage>
  )
}

export default  Utilisateur;
