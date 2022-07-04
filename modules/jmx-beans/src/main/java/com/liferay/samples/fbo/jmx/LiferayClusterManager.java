package com.liferay.samples.fbo.jmx;

import com.liferay.portal.kernel.cluster.ClusterExecutor;
import com.liferay.portal.kernel.cluster.ClusterNode;

import java.util.List;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"jmx.objectname=com.liferay.samples.fbo.jmx:classification=cluster,name=LiferayClusterManager",
		"jmx.objectname.cache.key=LiferayClusterManager"
	},
	service = DynamicMBean.class
)
public class LiferayClusterManager extends StandardMBean implements LiferayClusterManagerMBean {

	public LiferayClusterManager() throws NotCompliantMBeanException {
		super(LiferayClusterManagerMBean.class);
	}

	@Override
	public int getNodeCount() {

		List<ClusterNode> clusterNodes = _clusterExecutor.getClusterNodes();
		if(clusterNodes == null) {
			// Cluster link is not enabled
			return 0;
		} else {
			return clusterNodes.size();
		}
	}
	
	@Reference
	private ClusterExecutor _clusterExecutor;

}
