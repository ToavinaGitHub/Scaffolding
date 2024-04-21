import React, { useState, useEffect } from "react";
import { IonButton, IonModal,IonPage,IonIcon,IonAlert } from '@ionic/react';

import { trash,pencil } from "ionicons/icons";
const  District: React.FC = () => {
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
    const itemDetails = district.find((item: { idDistrict: any; }) => item.idDistrict === itemKey);
    setSelectedItem(itemDetails);
  };

 	const [district, setDistrict] = useState([]);
	
	const [region, setRegion] = useState([]);
	
	

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
      const response = await fetch(url + 'district', {
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
      const response = await fetch(url + 'district', {
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
      const response = await fetch(url + 'district', {
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

    	const handleInputNomDistrictChange = (event :any) => {
		setSelectedItem({ ...selectedItem, nomDistrict: event.target.value });
	};
	
	const handleSelectRegionChange = (event : any) => {
		setSelectedItem({ ...selectedItem, region: event.target.value });
	};
	
	const handleInputIdDistrictChange = (event :any) => {
		setSelectedItem({ ...selectedItem, idDistrict: event.target.value });
	};
	
	

    	useEffect(() => {
		const getDistrict = async () => {
			try {
				const response = await fetch(url + 'district?page='+ (currentPage - 1));
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setDistrict(data.content);
			setCount(data.totalElements)} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getDistrict();
	}, [currentPage]);
	useEffect(() => {
		const getRegion = async () => {
			try {
				const response = await fetch(url + 'region');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setRegion(data);
			} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getRegion();
	}, []);
	
  return (
    <IonPage>
      <div className="container">
        <div className="row justify-content-end">
          <div className="col">
            <div className="row">
              <IonButton onClick={handleShow}>
                Add District
              </IonButton>
            </div>

            <IonModal isOpen={show} onDidDismiss={handleClose}>
              <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
                	<div className="mb-3"> 
	 	<label className="form-label">nomDistrict</label> 
	 	<input className="form-control" type="text" name="nomDistrict" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Region</label> 
	 	<select className="form-control" name="region" id="select-region">
			{region.map((elt :any) => (
				<option value={elt.id}>{elt.nomRegion}</option>
			))}
			
		</select>
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
                			<th> Nom District </th>
			<th> Region </th>
			<th> Id District </th>

                <th></th>
              </tr>
            </thead>
            <tbody id="table-body">
              {district.map((item :any) => (
                <tr key={item.idDistrict}>
                  		<td>{item.nomDistrict}</td>
		<td>{item.region.nomRegion}</td>
		<td>{item.idDistrict}</td>

                  <td>
                    <IonButton onClick={() => handleDeleteClick(item)} color={"danger"}>
                      <IonIcon aria-hidden="true" icon={trash} />
                    </IonButton>
                    <IonButton onClick={() => handleSelectItem(item.idDistrict)} color={"success"}>
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
	 	<label className="form-label">Nom District</label> 
	 	<input className="form-control" type="text" name="nomDistrict" onChange={handleInputNomDistrictChange} value={selectedItem ? selectedItem.nomDistrict:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Region</label> 
	 	<select className="form-control" name="region" value={selectedItem ? selectedItem.region.id : ''} onChange={handleSelectRegionChange} >
			{region.map((elt : any) => (
		<option value={elt.id}>{elt.nomRegion}</option>
	))}
	
	
	</select>
	</div><div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="idDistrict" onChange={handleInputIdDistrictChange} value={selectedItem ? selectedItem.idDistrict:''} />
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

export default  District;
