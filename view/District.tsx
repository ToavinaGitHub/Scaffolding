import React, { useState, useEffect } from "react";
import { IonButton, IonModal,IonPage } from '@ionic/react';

const  District: React.FC = () => {
  const url = 'http://localhost:8080/';

  const [loading, setLoading] = useState(true);

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
    const itemDetails = district.find((item: { idDistrict: any; }) => item.idDistrict === itemKey);
    setSelectedItem(itemDetails);
  };

 	const [district, setDistrict] = useState([]);
	
	const [region, setRegion] = useState([]);
	
	

  //////////// SAVE
  const handleSaveSubmit = async (event : any) => {
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
  const handleDeleteClick = async (item : any) => {
    try {
      console.log(item);
      const response = await fetch(url + 'district', {
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
				const response = await fetch(url + 'district');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setDistrict(data);
			} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getDistrict();
	}, []);
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
	 	<label className="form-label">Nom District</label> 
	 	<input className="form-control" type="text" name="nomDistrict" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idDistrict</label> 
	 	<select className="form-control" name="region" id="select-region">
			{region.map((elt :any) => (
				<option value={elt.id}>{elt.nomRegion}</option>
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
                			<th> Nom District </th>
			<th> Id Region </th>
			<th> Id District </th>

                <th></th>
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
                    <IonButton onClick={() => handleDeleteClick(item)}>
                      Delete
                    </IonButton>
                  </td>
                  <td>
                    <IonButton onClick={() => handleSelectItem(item.idDistrict)}>
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
	 	<label className="form-label">Nom District</label> 
	 	<input className="form-control" type="text" name="nomDistrict" onChange={handleInputNomDistrictChange} value={selectedItem ? selectedItem.nomDistrict:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">idDistrict</label> 
	 	<select className="form-control" name="region">
			{region.map((elt : any) => (
		<option value={elt.id}>{elt.nomRegion}</option>
	))}
	
	
	</select>
	</div><div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="idDistrict" onChange={handleInputIdDistrictChange} value={selectedItem ? selectedItem.idDistrict:''} />
	</div>
	
              <IonButton type="submit">
                Save Changes
              </IonButton>
            </form>
          </IonModal>
        </div>
      </div>
    </IonPage>
  )
}

export default  District;
