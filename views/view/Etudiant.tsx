import React, { useState, useEffect } from "react";
import { IonButton, IonModal,IonPage } from '@ionic/react';

const  Etudiant: React.FC = () => {
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
    const itemDetails = etudiant.find((item: { id: any; }) => item.id === itemKey);
    setSelectedItem(itemDetails);
  };

 	const [etudiant, setEtudiant] = useState([]);
	
	

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
      const response = await fetch(url + 'etudiant', {
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
      const response = await fetch(url + 'etudiant', {
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
      const response = await fetch(url + 'etudiant', {
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

    	const handleInputSexeChange = (event :any) => {
		setSelectedItem({ ...selectedItem, sexe: event.target.value });
	};
	
	const handleInputIdChange = (event :any) => {
		setSelectedItem({ ...selectedItem, id: event.target.value });
	};
	
	const handleInputNomChange = (event :any) => {
		setSelectedItem({ ...selectedItem, nom: event.target.value });
	};
	
	const handleInputPrenomChange = (event :any) => {
		setSelectedItem({ ...selectedItem, prenom: event.target.value });
	};
	
	const handleInputDtnChange = (event :any) => {
		setSelectedItem({ ...selectedItem, dtn: event.target.value });
	};
	
	

    	useEffect(() => {
		const getEtudiant = async () => {
			try {
				const response = await fetch(url + 'etudiant?page='+ (currentPage - 1));
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setEtudiant(data.content);
			setCount(data.totalElements)} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getEtudiant();
	}, [currentPage]);
	
  return (
    <IonPage>
      <div className="container">
        <div className="row justify-content-end">
          <div className="col">
            <div className="row">
              <IonButton onClick={handleShow}>
                Add Etudiant
              </IonButton>
            </div>

            <IonModal isOpen={show} onDidDismiss={handleClose}>
              <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
                	<div className="mb-3"> 
	 	<label className="form-label">Sexe</label> 
	 	<input className="form-control" type="number" name="sexe" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Nom</label> 
	 	<input className="form-control" type="text" name="nom" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Prenom</label> 
	 	<input className="form-control" type="text" name="prenom" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Dtn</label> 
	 	<input className="form-control" type="Date" name="dtn" />
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
                			<th> Sexe </th>
			<th> Id </th>
			<th> Nom </th>
			<th> Prenom </th>
			<th> Dtn </th>

                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody id="table-body">
              {etudiant.map((item :any) => (
                <tr key={item.id}>
                  		<td>{item.sexe}</td>
		<td>{item.id}</td>
		<td>{item.nom}</td>
		<td>{item.prenom}</td>
		<td>{item.dtn}</td>

                  <td>
                    <IonButton onClick={() => handleDeleteClick(item)}>
                      Delete
                    </IonButton>
                  </td>
                  <td>
                    <IonButton onClick={() => handleSelectItem(item.id)}>
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
	 	<label className="form-label">Sexe</label> 
	 	<input className="form-control" type="number" name="sexe" onChange={handleInputSexeChange} value={selectedItem ? selectedItem.sexe:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="id" onChange={handleInputIdChange} value={selectedItem ? selectedItem.id:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Nom</label> 
	 	<input className="form-control" type="text" name="nom" onChange={handleInputNomChange} value={selectedItem ? selectedItem.nom:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Prenom</label> 
	 	<input className="form-control" type="text" name="prenom" onChange={handleInputPrenomChange} value={selectedItem ? selectedItem.prenom:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Dtn</label> 
	 	<input className="form-control" type="Date" name="dtn" onChange={handleInputDtnChange} value={selectedItem ? selectedItem.dtn:''} />
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
          <li className={`page-item ${currentPage === totalPageCount ? 'disabled' : ''}`}>
            <button className="page-link" onClick={() => setCurrentPage(prevPage => prevPage + 1)} disabled={currentPage === totalPageCount}>Next</button>
          </li>
        </ul>
      </nav>
      </div>
    </IonPage>
  )
}

export default  Etudiant;
